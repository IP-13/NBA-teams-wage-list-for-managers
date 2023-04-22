create table if not exists person
(
    id            int primary key,
    name          varchar(20),
    surname       varchar(20),
    date_of_birth date,
    height        int,
    weight        int
);


create table if not exists team
(
    id           int primary key,
    name         varchar(20),
    country      varchar(20)
);


create table if not exists coach
(
    id        int primary key,
    person_id int references person (id),
    team_id   int references team (id)
);


create table if not exists player
(
    id        int primary key,
    person_id int references person (id),
    team_id   int references team (id)
);


create table if not exists season
(
    id   int primary key,
    year int
);


create table if not exists players_statistics
(
    player_id int references player (id),
    season_id int references season (id),
    mpg       float,
    ppg       float,
    rpg       float,
    apg       float,
    bpg       float,
    spg       float,
    gp        int,
    is_po     boolean,
    primary key (player_id, season_id, is_po)
);


create table if not exists teams_statistics
(
    team_id   int references team (id),
    season_id int references season (id),
    wins      int,
    loses     int,
    primary key (team_id, season_id)
);


create table if not exists teams_statistics_po
(
    season_id       int references season (id),
    home_team_id    int references team (id),
    guest_team_id   int references team (id),
    home_team_wins  int,
    guest_team_wins int,
    stage           varchar(20),
    primary key (season_id, home_team_id, guest_team_id, stage)
);


create table if not exists annual_awards
(
    id                           int primary key,
    season_id                    int references season (id),
    mvp                          int references player (id),
    defensive_player_of_the_year int references player (id),
    rookie_of_the_year           int references player (id),
    coach_of_the_year            int references coach (id),
    six_player_of_the_year       int references player (id)
);


create table if not exists player_salary
(
    season_id int references season (id),
    player_id int references player (id),
    team_id   int references team (id),
    salary    int,
    primary key (season_id, player_id, team_id)
);


create table if not exists team_salary
(
    team_id      int references team (id),
    season_id    int references season (id),
    total_salary int,
    primary key (team_id, season_id)
);


create or replace function create_team_salary_function()
    returns trigger
    language plpgsql as
$$
declare
    num_of_teams int;
begin
    select (select count(*) from team) into num_of_teams;
    for team in 1..num_of_teams
        loop
            insert into team_salary (team_id, season_id, total_salary) values (team, new.id, 0);
        end loop;
    return new;
end;
$$;


create trigger create_team_salary_trigger
    after insert
    on season
    for each row
execute procedure create_team_salary_function();


create or replace function update_team_salary_function()
    returns trigger
    language plpgsql as
$$
begin
    update team_salary
    set total_salary = total_salary + new.salary
    where team_salary.team_id = new.team_id
      and team_salary.season_id = new.season_id;
    return new;
end;
$$;


create trigger update_team_salary_trigger
    after insert
    on player_salary
    for each row
execute procedure update_team_salary_function();


create or replace function reduce_team_salary_function()
    returns trigger
    language plpgsql as
$$
begin
    update team_salary
    set total_salary = total_salary - old.salary
    where team_salary.team_id = old.team_id
      and team_salary.season_id = old.season_id;
    return old;
end;
$$;


create trigger reduce_team_salary_trigger
    after delete
    on player_salary
    for each row
execute procedure reduce_team_salary_function();


create index player_index on player using hash(id);
create index player_salary_index on player_salary using hash(player_id);
create index team_salary_index on team_salary using hash(team_id);
create index player_stat_index on players_statistics using hash(player_id);


create or replace procedure add_person(_id int, _name text, _surname text, _date_of_birth date, _height int,
                                       _weight int)
    language plpgsql as
$$
begin
    insert into person (id, name, surname, date_of_birth, height, weight)
    values (_id, _name, _surname, _date_of_birth, _height, _weight);
end;
$$;


create or replace procedure add_team(_id int, _name text, _country text)
    language plpgsql as
$$
begin
    insert into team (id, name, country)
    values (_id, _name, _country);
end;
$$;


create or replace procedure add_coach(_id int, _person_id int, _team_id int)
    language plpgsql as
$$
begin
    insert into coach (id, person_id, team_id)
    values (_id, _person_id, _team_id);
end;
$$;


create or replace procedure add_player(_id int, _person_id int, _team_id int)
    language plpgsql as
$$
declare
    curr_num_of_players int;
begin
    select (select count(*) from player where player.team_id = _team_id) into curr_num_of_players;
    if curr_num_of_players = 15 then
        raise notice 'Only 15 players allowed in one team';
    else
        insert into player (id, person_id, team_id)
        values (_id, _person_id, _team_id);
    end if;
end;
$$;


create or replace procedure add_season(_id int, _year int)
    language plpgsql as
$$
begin
    insert into season (id, year)
    values (_id, _year);
end;
$$;


create or replace procedure add_player_stat(_player_id int, _season_id int, _mpg float, _ppg float, _rpg float,
                                            _apg float,
                                            _bpg float, _spg float, _gp int, _is_po boolean)
    language plpgsql as
$$
begin
    insert into players_statistics (player_id, season_id, mpg, ppg, rpg, apg, bpg, spg, gp, is_po)
    values (_player_id, _season_id, _mpg, _ppg, _rpg, _apg, _bpg, _spg, _gp, _is_po);
end;
$$;


create or replace procedure add_team_stat(_team_id int, _season_id int, _wins int, _loses int)
    language plpgsql as
$$
begin
    insert into teams_statistics (team_id, season_id, wins, loses)
    values (_team_id, _season_id, _wins, _loses);
end;
$$;


create or replace procedure add_team_stat_po(_season_id int, _home_team_id int, _guest_team_id int, _home_team_wins int,
                                             _guest_team_wins int, _stage text)
    language plpgsql as
$$
begin
    insert into teams_statistics_po (season_id, home_team_id, guest_team_id, home_team_wins, guest_team_wins, stage)
    values (_season_id, _home_team_id, _guest_team_id, _home_team_wins, _guest_team_wins, _stage);
end;
$$;


create or replace procedure add_annual_awards(id int, season_id int, mvp int, dpoy int, roty int, coach int,
                                              six_player int)
    language plpgsql as
$$
begin
    insert into annual_awards (id, season_id, mvp, defensive_player_of_the_year, rookie_of_the_year, coach_of_the_year,
                               six_player_of_the_year)
    values (id, season_id, mvp, dpoy, roty, coach, six_player);
end;
$$;


create or replace procedure add_player_salary(season_id int, player_id int, team_id int, salary int)
    language plpgsql as
$$
begin
    insert into player_salary (season_id, player_id, team_id, salary)
    values (season_id, player_id, team_id, salary);
end;
$$;


create or replace procedure change_player_team(_player_id int, _team_id int)
    language plpgsql as
$$
declare
    curr_num_of_players  int;
    num_of_small_players int;
begin
    select (select count(*) from player where player.team_id = _team_id) into curr_num_of_players;
    select (select count(*)
            from (select player.id, person.height, player.team_id
                  from player
                           join person on player.person_id = person.id) a
            where team_id = _team_id
              and height <= 180)
    into num_of_small_players;

    if (num_of_small_players = 1
        and (select id
             from (select player.id, person.height, player.team_id
                   from player
                            join person on player.person_id = person.id) b
             where height <= 180
               and team_id = _team_id limit 1) = _player_id) then
        raise notice 'Each team must contain at least one player whose height is less then or equal 180';
    else
        if curr_num_of_players = 15 then
            raise notice 'Only 15 players allowed in one team';
        else
            update player set team_id = _team_id where id = _player_id;
        end if;
    end if;
end;
$$;


create sequence if not exists person_seq;
create sequence if not exists team_seq;
create sequence if not exists coach_seq;
create sequence if not exists player_seq;
create sequence if not exists season_seq;
create sequence if not exists annual_awards_seq;


create or replace procedure fill_person_table()
    language plpgsql as
$$
declare
    curr_person   int;
    num_of_people int := 600;
begin
    for i in 1..num_of_people
        loop
            select nextval('person_seq') into curr_person;
            insert into person(id, name, surname, date_of_birth, height, weight)
            values (curr_person, ('person_' || curr_person || '_name'), ('person_' || curr_person || '_surname'),
                    (now()::date - 5000 - curr_person) :: date, 150 + (random() * 80) :: int,
                    80 + (random() * 70) :: int);
        end loop;
end
$$;


create or replace procedure fill_team_table()
    language plpgsql as
$$
declare
    curr_team    int;
    num_of_teams int := 30;
begin
    for i in 1..num_of_teams
        loop
            select nextval('team_seq') into curr_team;
            insert into team(id, name, country)
            values (curr_team, ('team_' || curr_team || '_name'), ('team_' || curr_team || '_country'));
        end loop;
end
$$;


create or replace procedure fill_coach_table()
    language plpgsql as
$$
declare
    curr_coach     int;
    num_of_coaches int := 30;
begin
    for i in 1..num_of_coaches
        loop
            select nextval('coach_seq') into curr_coach;
            insert into coach(id, person_id, team_id)
            values (curr_coach, curr_coach, curr_coach);
        end loop;
end
$$;


create or replace procedure fill_player_table()
    language plpgsql as
$$
declare
    curr_player    int;
    num_of_players int := 390;
begin
    for i in 1..num_of_players
        loop
            select nextval('player_seq') into curr_player;
            insert into player(id, person_id, team_id)
            values (curr_player, curr_player, (curr_player - 1) % currval('team_seq') + 1);
        end loop;
end
$$;


create or replace procedure fill_season_table()
    language plpgsql as
$$
declare
    curr_season    int;
    num_of_seasons int := 15;
begin
    for i in 1..num_of_seasons
        loop
            select nextval('season_seq') into curr_season;
            insert into season(id, year)
            values (curr_season, 1990 + curr_season);
        end loop;
end
$$;


create or replace procedure fill_players_statistics_table()
    language plpgsql as
$$
declare
    num_of_seasons int;
    num_of_players int;
begin
    select currval('season_seq') into num_of_seasons;
    select currval('player_seq') into num_of_players;
    for curr_season in 1..num_of_seasons
        loop
            for curr_player in 1..num_of_players
                loop
                    insert into players_statistics (player_id, season_id, mpg, ppg, rpg, apg, bpg, spg, gp, is_po)
                    values (curr_player, curr_season, trunc((random() * 48) :: numeric, 1),
                            trunc((random() * 35)::numeric, 1), trunc((random() * 13) :: numeric, 1),
                            trunc((random() * 11):: numeric, 1), trunc((random() * 3) :: numeric, 1),
                            trunc((random() * 2) :: numeric, 1), (random() * 27) :: int + 55, false);
                    insert into players_statistics (player_id, season_id, mpg, ppg, rpg, apg, bpg, spg, gp, is_po)
                    values (curr_player, curr_season, trunc((random() * 48) :: numeric, 1),
                            trunc((random() * 35) :: numeric, 1), trunc((random() * 13) :: numeric, 1),
                            trunc((random() * 11) :: numeric, 1), trunc((random() * 3)::numeric, 1),
                            trunc((random() * 2) :: numeric, 1), (random() * 25) :: int + 3, true);
                end loop;
        end loop;
end;
$$;


create or replace procedure fill_teams_statistics_table()
    language plpgsql as
$$
declare
    num_of_seasons int;
    num_of_teams   int;
    num_of_wins    int;
begin
    select currval('season_seq') into num_of_seasons;
    select currval('team_seq') into num_of_teams;
    for curr_season in 1..num_of_seasons
        loop
            for curr_team in 1..num_of_teams
                loop
                    select ((random() * 82) :: int) into num_of_wins;
                    insert into teams_statistics (team_id, season_id, wins, loses)
                    values (curr_team, curr_season, num_of_wins, 82 - num_of_wins);
                end loop;
        end loop;
end;
$$;


create or replace procedure fill_teams_statistics_po_table()
    language plpgsql as
$$
declare
    num_of_seasons  int;
    num_of_teams    int;
    home_team       int;
    guest_team      int;
    home_team_wins  int := 4;
    guest_team_wins int := 2;
begin
    select currval('season_seq') into num_of_seasons;
    select currval('team_seq') into num_of_teams;
    for curr_season in 1..num_of_seasons
        loop
            for i in 1..4
                loop
                    for j in 1..(2 ^ (4 - i))
                        loop
                            select j into home_team;
                            select j + 1 into guest_team;

                            insert into teams_statistics_po (season_id, home_team_id, guest_team_id,
                                                             home_team_wins, guest_team_wins, stage)
                            values (curr_season, home_team, guest_team, home_team_wins, guest_team_wins,
                                    'round ' || i);
                        end loop;
                end loop;
        end loop;
end;
$$;


create or replace procedure fill_annual_awards_table()
    language plpgsql as
$$
declare
    num_of_seasons int;
    num_of_players int;
    num_of_coaches int;
begin
    select currval('season_seq') into num_of_seasons;
    select currval('player_seq') into num_of_players;
    select currval('coach_seq') into num_of_coaches;
    for curr_season in 1..num_of_seasons
        loop
            insert into annual_awards(id, season_id, mvp, defensive_player_of_the_year, rookie_of_the_year,
                                      coach_of_the_year, six_player_of_the_year)
            values (nextval('annual_awards_seq'), curr_season,
                    (random() * (num_of_players - 1)) + 1 :: int,
                    (random() * (num_of_players - 1)) + 1 :: int,
                    (random() * (num_of_players - 1)) + 1 :: int,
                    (random() * (num_of_coaches - 1)) + 1 :: int,
                    (random() * (num_of_players - 1)) + 1 :: int);
        end loop;
end
$$;


create or replace procedure fill_player_salary_table()
    language plpgsql as
$$
declare
    num_of_seasons int;
    num_of_players int;
    num_of_teams   int;
begin
    select currval('season_seq') into num_of_seasons;
    select currval('player_seq') into num_of_players;
    select currval('team_seq') into num_of_teams;
    for curr_season in 1..num_of_seasons
        loop
            for curr_player in 1..num_of_players
                loop
                    insert into player_salary(season_id, player_id, team_id, salary)
                    values (curr_season, curr_player, (random() * (num_of_teams - 1)) :: int + 1,
                            trunc((5 + random() * 35) :: numeric, 2));
                end loop;
        end loop;
end
$$;


call fill_person_table();
call fill_team_table();
call fill_coach_table();
call fill_player_table();
call fill_season_table();
call fill_players_statistics_table();
call fill_teams_statistics_table();
call fill_teams_statistics_po_table();
call fill_annual_awards_table();
call fill_player_salary_table();
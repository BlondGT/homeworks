select title from cinema.movies;

select director from cinema.movies;

select title, director from cinema.movies;

select title, year from cinema.movies;

select * from cinema.movies;

select title from cinema.movies
where year between 2000 and 2010;

select title from cinema.movies
where year not between 2000 and 2010;

select title, year from cinema.movies
where year in (2000, 2010, 2020);

select title from cinema.movies
where title like 'B%'
union
select title from cinema.movies
where title like 'S%'
union
select title from cinema.movies
where title like'P%';

select distinct director from cinema.movies
where year > 2005
order by director asc;

select cinema.movies.title, cinema.box_office.domestic_sale, cinema.box_office.international_sale
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id;

select cinema.movies.title, cinema.movies.director, cinema.box_office.rating
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id
where cinema.box_office.international_sale > cinema.box_office.domestic_sale;

select cinema.movies.title
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id
order by cinema.box_office.rating desc;

select cinema.movies.title,
from cinema.movies
cross join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id

select cinema.movies.title, sum(cinema.box_office.international_sale + cinema.box_office.domestic_sale)
as total_sale
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id
group by cinema.movies.title;

select title from cinema.movies
where mod (year, 2) = 0;

select cinema.movies.director, count(cinema.movies.title), avg(cinema.box_office.rating)
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id
group by cinema.movies.director;

select cinema.movies.director,
sum(cinema.box_office.international_sale) as total_international_sale,
sum (cinema.box_office.domestic_sale) as total_domestic_sale
from cinema.movies
join cinema.box_office
on cinema.movies.id=cinema.box_office.movie_id
group by cinema.movies.director;
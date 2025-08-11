create table Artist(
	ArtistId int primary key auto_increment,
    Name varchar(200)
);

create table Song(
	SongId int primary key auto_increment,
    ArtistId int,
    Title varchar(200),
	foreign key (ArtistId) references Artist(ArtistId)
);
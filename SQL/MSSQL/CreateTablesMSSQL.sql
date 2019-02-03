IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Movies' and xtype='U')
CREATE TABLE Movies(
	_id INT not null,
	_title VARCHAR(255) not null,
	_releaseYear INT not null,
	_duration INT not null,
	PRIMARY KEY (_iD)
)

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Cinemas' and xtype='U')
CREATE TABLE Cinemas(
	_id INT not null,
	_name VARCHAR(255) not null,
	_city VARCHAR(255) not null,
	PRIMARY KEY (_id)
)

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Theaters' and xtype='U')
CREATE TABLE Theaters(
	_id INT not null,
	_name VARCHAR(255) not null,
	_rows INT not null,
	_seats INT not null,
	_availableSeats INT null,
	_cid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_cid) REFERENCES Cinemas(_id)
)

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Sessions' and xtype='U')
CREATE TABLE Sessions (
	_id INT not null,
	_date DATETIME not null,
	_mid INT not null,
	_tid INT not null,
	_cid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_mid) REFERENCES Movies(_id),
	FOREIGN KEY (_tid) REFERENCES Theaters(_id),
	FOREIGN KEY (_cid) REFERENCES Cinemas(_id)
)

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Tickets' and xtype='U')
CREATE TABLE Tickets (
	_id INT not null,
	_row char not null,
	_seat INT not null,
	_sid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_sid)	REFERENCES Sessions(_id)
)

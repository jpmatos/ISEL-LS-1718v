CREATE TABLE IF NOT EXISTS Movies(
	_id INT not null,
	_title VARCHAR(255) not null,
	_releaseYear INT not null,
	_duration INT not null,
	PRIMARY KEY (_iD)
);

CREATE TABLE IF NOT EXISTS Cinemas(
	_id INT not null,
	_name VARCHAR(255) not null,
	_city VARCHAR(255) not null,
	PRIMARY KEY (_id)
);

CREATE TABLE IF NOT EXISTS Theaters(
	_id INT not null,
	_name VARCHAR(255) not null,
	_rows INT not null,
	_seats INT not null,
	_availableSeats INT null,
	_cid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_cid) REFERENCES Cinemas(_id)
);

CREATE TABLE IF NOT EXISTS Sessions (
	_id INT not null,
	_date timestamp not null,
	_mid INT not null,
	_tid INT not null,
	_cid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_mid) REFERENCES Movies(_id),
	FOREIGN KEY (_tid) REFERENCES Theaters(_id),
	FOREIGN KEY (_cid) REFERENCES Cinemas(_id)
);

CREATE TABLE IF NOT EXISTS Tickets (
	_id INT not null,
	_row char not null,
	_seat INT not null,
	_sid INT not null,
	PRIMARY KEY (_id),
	FOREIGN KEY (_sid)	REFERENCES Sessions(_id)
);

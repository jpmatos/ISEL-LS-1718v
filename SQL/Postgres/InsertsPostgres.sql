INSERT INTO Movies (_id, _title, _releaseYear, _duration) VALUES
				(01, 'Movie3', 2013, 90),
				(02, 'Movie1', 2015, 100),
				(04, 'Movie4', 2017, 80),
				(06, 'Movie8', 2018, 120);

INSERT INTO Cinemas (_id, _name, _city) VALUES
				(001, 'Cinema2', 'City2'),
				(002, 'Cinema1', 'City1'),
				(005, 'Cinema5', 'City6'),
				(007, 'Cinema6', 'City3');

INSERT INTO Theaters (_id, _name, _rows, _availableSeats, _seats , _cid) VALUES
				(02, 'Theater2', 10, 20, 20, 002),
				(04, 'Theater1', 15, 40, 40, 005),
				(05, 'Theater5', 10, 25, 25, 007),
				(09, 'Theater6', 20, 35, 35, 005);

INSERT INTO Sessions (_id, _date, _mid, _tid, _cid) VALUES
				(0003, '2013-01-01 00:00:00', 02, 02, 002),
				(0006, '2015-01-01 00:00:00', 01, 04, 001),
				(0010, '2017-01-01 00:00:00', 04, 05, 007),
				(0014, '2018-01-01 00:00:00', 01, 09, 005),				
				(0022, '2018-03-22 12:00:00', 04, 04, 005);

INSERT INTO Tickets (_id, _row, _seat, _sid) VALUES
				(01, 'A', 03, 0003),
				(02, 'B', 05, 0003),
				(03, 'D', 12, 0006),
				(04, 'A', 02, 0014);

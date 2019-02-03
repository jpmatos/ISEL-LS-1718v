SELECT * FROM Sessions
SELECT * FROM Movies
SELECT * FROM Cinemas
SELECT * FROM Tickets
SELECT * FROM Theaters
SELECT * from theaters INNER JOIN cinemas ON (theaters._cid = cinemas._id) WHERE cinemas._id = 5 AND theaters._id = 4 AND 1=1 

SELECT * FROM Tickets T INNER JOIN Sessions S ON (S._id=T._sid) INNER JOIN Theaters Th ON (Th._id=S._tid)

SELECT * FROM Theaters T INNER JOIN Sessions S ON (T._id=S._tid) WHERE S._id = 1

SELECT * FROM Theaters T INNER JOIN Sessions S ON (1=S._tid)

SELECT * from sessions INNER JOIN movies ON (sessions._mid = movies._id) WHERE movies._id = 2 AND 1=1 AND Movies._id IN (2)
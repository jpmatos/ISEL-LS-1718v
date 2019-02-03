package pt.isel.ls;

public class PostTests {
//    @Test
//    public void postMovies(){
//        SQLCommands.CreateDBsIfNotExists();
//        String command = "POST /movies title=title1&releaseYear=2001&duration=90";
//        Movies movies = (Movies) CommandProcessor.process(command).iterator().next();
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies moviesFound = new Movies();
//        Iterable<Movies> res = m.GetAll();
//        for (Movies mov: res) {
//            if (mov.equalsTest(movies))
//                    moviesFound = mov;
//        }
//        m.Delete(movies);
//        assertEquals(movies.getTitle(), moviesFound.getTitle());
//    }
//
//    @Test
//    public void postCinemas(){
//        SQLCommands.CreateDBsIfNotExists();
//        String command = "POST /cinemas name=name101&city=city101";
//        Cinemas cinemas = (Cinemas) CommandProcessor.process(command).iterator().next();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas cinemasFound = new Cinemas();
//        Iterable<Cinemas> res = c.GetAll();
//        for (Cinemas cin: res) {
//            if (cin.equalsTest(cinemas))
//                    cinemasFound = cin;
//        }
//        c.Delete(cinemas);
//        assertEquals(cinemas.getName(), cinemasFound.getName());
//    }
//
//    @Test
//    public void postCinemasIdTheaters(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas cin = new Cinemas(101, "name101", "city101");
//        c.Insert(cin);
//
//        String command = "POST /cinemas/101/theaters name=theater11&rows=10&seats=10";
//        Theaters theaters = (Theaters) CommandProcessor.process(command).iterator().next();
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters theatersFound = new Theaters();
//        Iterable<Theaters> res = t.GetAll();
//        for (Theaters theat: res) {
//            if (theat.equalsTest(theaters))
//                theatersFound=theat;
//        }
//        t.Delete(theaters);
//        c.Delete(cin);
//        assertEquals(theaters.getName(), theatersFound.getName());
//    }
//
//    @Test
//    public void postCinemasIdTheatersIdSessions(){
//        SQLCommands.CreateDBsIfNotExists();
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies mov = new Movies(1, "title1", 2001, 90);
//        m.Insert(mov);
//
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas cin = new Cinemas(101, "name101", "city101");
//        c.Insert(cin);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters theat = new Theaters(11, "name11", 10, 20, 20, 101);
//        t.Insert(theat);
//
//        String command = "POST /cinemas/101/theaters/11/sessions date=2001-01-01+14:30:10&mid=1";
//        Sessions sessions = (Sessions) CommandProcessor.process(command).iterator().next();
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions sessionsFound = new Sessions();
//        Iterable<Sessions> res = s.GetAll();
//        for (Sessions ses: res) {
//            if(ses.equalsTest(sessions))
//                sessionsFound = ses;
//        }
//
//        s.Delete(sessions);
//        t.Delete(theat);
//        c.Delete(cin);
//        m.Delete(mov);
//        assertEquals(sessions.getMid(), sessionsFound.getMid());
//    }
//
//    @Test
//    public void postCinemasIdTheatersdSessionsIdTickets(){
//        SQLCommands.CreateDBsIfNotExists();
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies mov = new Movies(1, "title1", 2001, 90);
//        m.Insert(mov);
//
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas cin = new Cinemas(101, "name101", "city101");
//        c.Insert(cin);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters theat = new Theaters(11, "name11", 10, 20, 20, 101);
//        t.Insert(theat);
//
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions sess = new Sessions(1001,"2018-04-17 12:30:00",1,11,101);
//        s.Insert(sess);
//
//        String command = "POST /cinemas/101/theaters/11/sessions/1001/tickets row=Z&seat=12";
//        Tickets tickets = (Tickets) CommandProcessor.process(command).iterator().next();
//        TicketsDataMapper tk = new TicketsDataMapper();
//        Tickets ticketsFound = new Tickets();
//        Iterable<Tickets> res = tk.GetAll();
//        for (Tickets tks: res) {
//            if(tks.equalsTest(tickets))
//                ticketsFound = tks;
//        }
//
//        tk.Delete(tickets);
//        s.Delete(sess);
//        t.Delete(theat);
//        c.Delete(cin);
//        m.Delete(mov);
//        assertEquals(tickets.getId(), ticketsFound.getId());
//    }
}

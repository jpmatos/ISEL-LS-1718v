package pt.isel.ls;

public class GetTests {
//    @Test
//    public void getCinemas(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102"),
//                new Cinemas(103, "name103", "city103"),
//                new Cinemas(104, "name104", "city104")
//        };
//        c.InsertArray(cins);
//        String command = "GET /cinemas";
//        Iterable<Cinemas> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Cinemas p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//        c.DeleteArray(cins);
//        assertEquals(4, count);
//    }
//
//    @Test
//    public void getCinemasId() {
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cinemas = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102"),
//                new Cinemas(103, "name103", "city103"),
//                new Cinemas(104, "name104", "city104")
//        };
//        c.InsertArray(cinemas);
//
//        String command = "GET /cinemas/104";
//        Cinemas cinemasFound = (Cinemas) CommandProcessor.process(command).iterator().next();
//        c.DeleteArray(cinemas);
//        assertEquals(104, cinemasFound.getiD());
//    }
//
//    @Test
//    public void getCinemasIdTheaters() {
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        String command = "GET /cinemas/101/theaters";
//        Iterable<Theaters> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Theaters p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        assertEquals(2, count);
//    }
//
//    @Test
//    public void getCinemasIdTheatersId() {
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cinemas = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cinemas);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        String command = "GET /cinemas/102/theaters/13";
//        Theaters theatersGet = (Theaters) CommandProcessor.process(command).iterator().next();
//        t.DeleteArray(theaters);
//        c.DeleteArray(cinemas);
//        assertEquals(13, theatersGet.getiD());
//    }
//
//    @Test
//    public void getCinemasIdTheatersIdSessions(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, "2015-01-01 00:00:00", 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        String command = "GET /cinemas/101/theaters/14/sessions";
//        Iterable<Sessions> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Sessions p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(1, count);
//    }
//
//    @Test
//    public void getCinemasIdSessions(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, "2015-01-01 00:00:00", 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        String command = "GET /cinemas/101/sessions";
//        Iterable<Sessions> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Sessions p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(2, count);
//    }
//
//    @Test
//    public void getCinemasIdSessionsId(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cinemas = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cinemas);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, "2015-01-01 00:00:00", 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        String command = "GET /cinemas/101/sessions/1001";
//        Sessions sessionsGet = (Sessions) CommandProcessor.process(command).iterator().next();
//
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cinemas);
//        m.DeleteArray(movies);
//        assertEquals(1001, sessionsGet.getiD());
//    }
//
//    @Test
//    public void getCinemasIdTheatersIdSessionsToday(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        LocalDateTime today = LocalDateTime.now();
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, dtf.format(today), 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        String command = "GET /cinemas/102/theaters/15/sessions/today";
//        Iterable<Sessions> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Sessions p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(1, count);
//    }
//
//    @Test
//    public void getCinemasIdTheatersIdSessionsDate(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, "2019-05-11 12:00:00", 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        String command = "GET /cinemas/102/theaters/15/sessions/date/2019+05+11";
//        Iterable<Sessions> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Sessions p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(1, count);
//    }
//
//    @Test
//    public void getCinemasIdTheatersIdSessionsIdTickets(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        LocalDateTime today = LocalDateTime.now();
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, dtf.format(today), 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        TicketsDataMapper tk = new TicketsDataMapper();
//        Tickets[] tickets = {
//                new Tickets(2001,"A",5,1001),
//                new Tickets(2002,"F",6,1001),
//                new Tickets(2003,"A",7,1001),
//                new Tickets(2004,"B",1,1002),
//                new Tickets(2005,"C",5,1003),
//                new Tickets(2006,"D",5,1004),
//                new Tickets(2007,"G",2,1005)
//        };
//        tk.InsertArray(tickets);
//
//
//        String command = "GET /cinemas/101/theaters/11/sessions/1001/tickets";
//        Iterable<Tickets> res = CommandProcessor.process(command);
//        int count = 0;
//        for (Tickets p : res) {
//            System.out.println(p.toString());
//            count++;
//        }
//
//        tk.DeleteArray(tickets);
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(3, count);
//    }
//
//    @Test
//    public void getCinemasIdTheatersIdSessionsIdTicketsId(){
//        SQLCommands.CreateDBsIfNotExists();
//        CinemasDataMapper c = new CinemasDataMapper();
//        Cinemas[] cins = {
//                new Cinemas(101, "name101", "city101"),
//                new Cinemas(102, "name102", "city102")
//        };
//        c.InsertArray(cins);
//
//        TheatersDataMapper t = new TheatersDataMapper();
//        Theaters[] theaters = {
//                new Theaters(11, "name11", 10, 10, 10, 101),
//                new Theaters(12, "name12", 10, 10, 10, 102),
//                new Theaters(13, "name13", 10, 10, 10, 102),
//                new Theaters(14, "name14", 10, 10, 10, 101),
//                new Theaters(15, "name15", 10, 10, 10, 102)
//        };
//        t.InsertArray(theaters);
//
//        MoviesDataMapper m = new MoviesDataMapper();
//        Movies[] movies = {
//                new Movies(1, "title1", 2001, 90),
//                new Movies(2, "title2", 2002, 90)
//        };
//        m.InsertArray(movies);
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        LocalDateTime today = LocalDateTime.now();
//        SessionsDataMapper s = new SessionsDataMapper();
//        Sessions[] sessions = {
//                new Sessions(1001, "2011-01-01 00:00:00", 1, 11, 101),
//                new Sessions(1002, "2012-01-01 00:00:00", 2, 12, 102),
//                new Sessions(1003, "2013-01-01 00:00:00", 1, 13, 102),
//                new Sessions(1004, "2014-01-01 00:00:00", 2, 14, 101),
//                new Sessions(1005, dtf.format(today), 1, 15, 102)
//        };
//        s.InsertArray(sessions);
//
//        TicketsDataMapper tk = new TicketsDataMapper();
//        Tickets[] tickets = {
//                new Tickets(2001,"A",5,1001),
//                new Tickets(2002,"F",6,1001),
//                new Tickets(2003,"A",7,1001),
//                new Tickets(2004,"B",1,1002),
//                new Tickets(2005,"C",5,1003),
//                new Tickets(2006,"D",5,1004),
//                new Tickets(2007,"G",2,1005)
//        };
//        tk.InsertArray(tickets);
//
//        String command = "GET /cinemas/101/theaters/11/sessions/1001/tickets/2001";
//        Tickets ticketsGet = (Tickets) CommandProcessor.process(command).iterator().next();
//
//        tk.DeleteArray(tickets);
//        s.DeleteArray(sessions);
//        t.DeleteArray(theaters);
//        c.DeleteArray(cins);
//        m.DeleteArray(movies);
//        assertEquals(2001, ticketsGet.getId());
//    }
}

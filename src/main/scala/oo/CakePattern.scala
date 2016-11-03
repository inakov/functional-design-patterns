package oo

/**
  * Created by inakov on 02.11.16.
  */
object CakePattern extends App {
  case class Movie(id: String, title: String)
  case class Video(movieId: String)
  case class DecoratedMovie(movie: Movie, video: Video)

  trait MovieDaoComponent{
    trait MovieDao{
      def getMovie(id: String): Movie
    }
  }

  trait MovieDaoComponentImpl extends MovieDaoComponent{
    class MovieDaoImpl extends MovieDao{
      override def getMovie(id: String): Movie = Movie(id, "Random Title")
    }
  }

  trait MovieDaoComponentMock extends MovieDaoComponent{
    class MovieDaoMock extends MovieDao{
      override def getMovie(id: String): Movie = Movie("1", "Test Movie")
    }
  }

  trait FavoritesMoviesComponent {
    trait FavoritesService {
      def getFavoritesVideos(userId: String): List[Video]
    }
  }

  trait FavoritesMoviesComponentImpl extends FavoritesMoviesComponent{
    class FavoritesServiceImpl extends FavoritesService{
      override def getFavoritesVideos(userId: String): List[Video] = List(Video("6"), Video("10"))
    }
  }

  trait FavoritesServiceComponentMock extends FavoritesMoviesComponent{
    class FavoritesServiceMock extends FavoritesService{
      override def getFavoritesVideos(userId: String): List[Video] = List(Video("1"))
    }
  }

  trait MovieServiceComponentImpl {
    this: MovieDaoComponent with FavoritesMoviesComponent =>

    val favoritesService: FavoritesService
    val movieDao: MovieDao

    class MovieServiceImpl {
      def getFavoriteMovies(userId: String): List[DecoratedMovie] =
        for{
          favorite <- favoritesService.getFavoritesVideos(userId)
          movie = movieDao.getMovie(favorite.movieId)
        } yield DecoratedMovie(movie, favorite)
    }
  }


  object ComponentRegister
    extends MovieServiceComponentImpl with FavoritesMoviesComponentImpl with MovieDaoComponentImpl {

    override val favoritesService: FavoritesService = new FavoritesServiceImpl
    override val movieDao: MovieDao = new MovieDaoImpl

    val movieService = new MovieServiceImpl
  }

  object TestComponentRegister
    extends MovieServiceComponentImpl with FavoritesServiceComponentMock with MovieDaoComponentMock{
    override val favoritesService: FavoritesService = new FavoritesServiceMock
    override val movieDao: MovieDao = new MovieDaoMock

    val movieService = new MovieServiceImpl
  }

  import ComponentRegister._
  println(movieService.getFavoriteMovies("10"))

}

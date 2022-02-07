package com.android.moviecatalogueapp.utils

import com.android.moviecatalogueapp.data.source.remote.response.*

object DataDummyTest {

    fun generateDummyMovie(): List<MoviesResultsItem> {

        val movies = ArrayList<MoviesResultsItem>()

        movies.add(
            MoviesResultsItem(
                id = 524434,
                title = "Eternals",
                releaseDate = "2021-11-03",
                overview = "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                genreIds = listOf(
                    28,
                    12,
                    14,
                    878
                ),
                voteAverage = 7.3,
                posterPath = "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                backdropPath = "/k2twTjSddgLc1oFFHVibfxp2kQV.jpg"
            )
        )

        movies.add(
            MoviesResultsItem(
                id = 585083,
                title = "Hotel Transylvania: Transformania",
                releaseDate = "2022-01-13",
                overview = "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                genreIds = listOf(
                    16,
                    10751,
                    14,
                    35,
                    12
                ),
                voteAverage = 7.7,
                posterPath = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                backdropPath = "/m3Ys7UDzUzOyoPYKzp4GhKKokUr.jpg"
            )
        )

        movies.add(
            MoviesResultsItem(
                id = 634649,
                title = "Spider-Man: No Way Home",
                releaseDate = "2021-12-15",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                genreIds = listOf(
                    28,
                    12,
                    878
                ),
                voteAverage = 8.5,
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg"
            )
        )

        return movies
    }

    fun generateDummyDetailMovies(): MoviesDetailResponse {
        return MoviesDetailResponse(
            id = 524434,
            title = "Eternals",
            releaseDate = "2021-11-03",
            overview = "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
            genres = arrayListOf(
                GenresItem("Action", 28),
                GenresItem("Adventure", 12),
                GenresItem("Fantasy", 14),
                GenresItem("Science Fiction", 878)
            ),
            voteAverage = 7.3,
            posterPath = "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
            backdropPath = "/k2twTjSddgLc1oFFHVibfxp2kQV.jpg"
        )
    }

    fun generateDummyTvShows(): List<TvShowsResultsItem> {

        val tvShows = ArrayList<TvShowsResultsItem>()

        tvShows.add(
            TvShowsResultsItem(
                id = 85552,
                name = "Euphoria",
                firstAirDate = "2019-06-16",
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                genreIds = listOf(
                    18,
                    5
                ),
                voteAverage = 8.4,
                posterPath = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                backdropPath = "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg"
            )
        )

        tvShows.add(
            TvShowsResultsItem(
                id = 110492,
                name = "Peacemaker",
                firstAirDate = "2022-01-13",
                overview = "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.",
                genreIds = listOf(
                    10759,
                    35,
                    10765
                ),
                voteAverage = 8.5,
                posterPath = "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                backdropPath = "/ctxm191q5o3axFzQsvNPlbKoSYv.jpg"
            )
        )

        tvShows.add(
            TvShowsResultsItem(
                id = 115036,
                name = "The Book of Boba Fett",
                firstAirDate = "2021-12-29",
                overview = "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                genreIds = listOf(
                    10759,
                    10765
                ),
                voteAverage = 8.1,
                posterPath = "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
                backdropPath = "/sjx6zjQI2dLGtEL0HGWsnq6UyLU.jpg"
            )
        )

        return tvShows
    }

    fun generateDummyDetailTvShows(): TvShowsDetailResponse {
        return TvShowsDetailResponse(
            id = 85552,
            name = "Euphoria",
            firstAirDate = "2019-06-16",
            overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            genres = arrayListOf(
                GenresItem("Drama", 18),
                GenresItem("Comedy", 5)
            ),
            voteAverage = 8.4,
            posterPath = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            backdropPath = "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg"
        )
    }

}
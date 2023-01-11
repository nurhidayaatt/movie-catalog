package com.nurhidayaatt.core.utils

import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity
import com.nurhidayaatt.core.data.source.remote.network.ApiResponse
import com.nurhidayaatt.core.data.source.remote.response.movie.MovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.TvShowResponse
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataDummy {

    fun generateDummyMovieResponse(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        emit(ApiResponse.Success(DataMapper.mapMovieDomainToResponses(generateDummyMovie())))
    }

    fun generateDummyMovieEntity(): Flow<List<MovieEntity>> = flow {
        emit(DataMapper.mapMovieDomainToEntity(generateDummyMovie()))
    }

    fun generateDummyMovie(): List<Movie> {
        val movie = mutableListOf<Movie>()
        movie.add(
            Movie(
                id = 76600,
                overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
                backdropPath = "/s16H6tpK2utvwDtzZ8Qy4qm5Emw.jpg",
                posterPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                releaseDate = "2022-12-14",
                title = "Avatar: The Way of Water",
                voteAverage = 7.7,
                voteCount = 3251
            )
        )
        movie.add(
            Movie(
                id = 899112,
                overview = "When a team of mercenaries breaks into a wealthy family compound on Christmas Eve, taking everyone inside hostage, the team isn’t prepared for a surprise combatant: Santa Claus is on the grounds, and he’s about to show why this Nick is no saint.",
                backdropPath = "/6mEYUYdkKoVCMeYf3rTFj0L1uyv.jpg",
                posterPath = "/1XSYOP0JjjyMz1irihvWywro82r.jpg",
                releaseDate = "2022-11-30",
                title = "Violent Night",
                voteAverage = 7.7,
                voteCount = 689
            )
        )
        movie.add(
            Movie(
                id = 411,
                overview = "Siblings Lucy, Edmund, Susan and Peter step through a magical wardrobe and find the land of Narnia. There, they discover a charming, once peaceful kingdom that has been plunged into eternal winter by the evil White Witch, Jadis. Aided by the wise and magnificent lion, Aslan, the children lead Narnia into a spectacular, climactic battle to be free of the Witch's glacial powers forever.",
                backdropPath = "/tuDhEdza074bA497bO9WFEPs6O6.jpg",
                posterPath = "/iREd0rNCjYdf5Ar0vfaW32yrkm.jpg",
                releaseDate = "2005-12-07",
                title = "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
                voteAverage = 7.1,
                voteCount = 9068
            )
        )
        movie.add(
            Movie(
                id = 661374,
                overview = "World-famous detective Benoit Blanc heads to Greece to peel back the layers of a mystery surrounding a tech billionaire and his eclectic crew of friends.",
                backdropPath = "/lsN1wAbqCvUPKEhkEI9pQSSiTjU.jpg",
                posterPath = "/vDGr1YdrlfbU9wxTOdpf3zChmv9.jpg",
                releaseDate = "2022-11-23",
                title = "Glass Onion: A Knives Out Mystery",
                voteAverage = 7.0,
                voteCount = 1734
            )
        )
        movie.add(
            Movie(
                id = 436270,
                overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                backdropPath = "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                posterPath = "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
                releaseDate = "2022-10-19",
                title = "Black Adam",
                voteAverage = 7.2,
                voteCount = 3416
            )
        )
        return movie
    }

    fun generateDummyTvShowResponse(): Flow<ApiResponse<List<TvShowResponse>>> = flow {
        emit(ApiResponse.Success(DataMapper.mapTvShowDomainToResponses(generateDummyTvShow())))
    }

    fun generateDummyTvShowEntity(): Flow<List<TvShowEntity>> = flow {
        emit(DataMapper.mapTvShowDomainToEntity(generateDummyTvShow()))
    }

    fun generateDummyTvShow(): List<TvShow> {
        val tvShow = mutableListOf<TvShow>()

        tvShow.add(
            TvShow(
                id = 119051,
                firstAirDate = "2022-11-23",
                name = "Wednesday",
                overview = "Wednesday Addams is sent to Nevermore Academy, a bizarre boarding school where she attempts to master her psychic powers, stop a monstrous killing spree of the town citizens, and solve the supernatural mystery that affected her family 25 years ago — all while navigating her new relationships.",
                backdropPath = "/iHSwvRVsRyxpX7FE7GbviaDvgGZ.jpg",
                posterPath = "/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
                voteAverage = 8.7,
                voteCount = 4573
            )
        )
        tvShow.add(
            TvShow(
                id = 36361,
                firstAirDate = "2005-09-05",
                name = "Ulice",
                overview = "Ulice is a Czech soap opera produced and broadcast by Nova. In the Czech language Ulice means street.\n\nThe show describes the lives of the Farský, Jordán, Boháč, Nikl, and Liška families and many other people that live in Prague. Their daily battle against real problems of living in a modern world like divorce, love, betrayal and illness or disease. Ulice often shows crime.",
                backdropPath = null,
                posterPath = "/3ayWL13P1HeRnyVL9lU9flOdZjq.jpg",
                voteAverage = 2.1,
                voteCount = 8
            )
        )
        tvShow.add(
            TvShow(
                id = 73586,
                firstAirDate = "2018-06-20",
                name = "Yellowstone",
                overview = "Follow the violent world of the Dutton family, who controls the largest contiguous ranch in the United States. Led by their patriarch John Dutton, the family defends their property against constant attack by land developers, an Indian reservation, and America’s first National Park.",
                backdropPath = "/hFFAYkK8XkvIlQss9GYxo8TOhFi.jpg",
                posterPath = "/peNC0eyc3TQJa6x4TdKcBPNP4t0.jpg",
                voteAverage = 8.1,
                voteCount = 1367
            )
        )
        tvShow.add(
            TvShow(
                id = 115646,
                firstAirDate = "2021-01-25",
                name = "Lisa",
                overview = "",
                backdropPath = "/rv5gu2gYbOEYoArzH7bqJuMxvBB.jpg",
                posterPath = "/w2nOl7KhwcUj11YxEi9Nknj9cqu.jpg",
                voteAverage = 6.4,
                voteCount = 35
            )
        )
        tvShow.add(
            TvShow(
                id = 211239,
                firstAirDate = "2022-10-03",
                name = "The Love in Your Eyes",
                overview = "Depicts the second love of a rogue daughter-in-law who appears in a 30-year-old gomtang restaurant, and a confident single mother Young Ih who says anything no matter what.\n\nJang Kyung Joon, who has the charm of a black-and-white man. Even though he shows the coolness of self-restraint from a large company that doesn't show his emotions easily, he shows both sides of being friendly in front of Lee Young Ih. Lee Young Ih, a single mother of steel mentality and a daughter-in-law with a bad attitude. She is sometimes angry, but she is a colorful charmer full of justice. Kim Hae Mi, her mother's daughter who has both her work and love in her hands, and Jang Kyung Joon's fiancée. It forms a tense rivalry with Bae Nouri. Jang Se Joon, the half-brother of Jang Kyung Joon, who has a friendly personality.",
                backdropPath = "/jLH03sLbkAGxrThdW6b2lrwwE4X.jpg",
                posterPath = "/gJnVJ3l4p1GDvGpwFedXhAJG3qE.jpg",
                voteAverage = 3.5,
                voteCount = 2
            )
        )
        return tvShow
    }
}
package ru.shurikus.coreNetwork.interceptors


import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import ru.shurikus.coreNetwork.BuildConfig

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("/3/stub") -> getListMoviesJson
                else -> return chain.proceed(chain.request())
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}

const val getListMoviesJson = """
{
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/1DBDwevWS8OhiT3wqqlW7KGPd6m.jpg",
      "id": 985939,
      "title": "Fall",
      "original_language": "en",
      "original_title": "Fall",
      "overview": "For best friends Becky and Hunter, life is all about conquering fears and pushing limits. But after they climb 2,000 feet to the top of a remote, abandoned radio tower, they find themselves stranded with no way down. Now Becky and Hunter’s expert climbing skills will be put to the ultimate test as they desperately fight to survive the elements, a lack of supplies, and vertigo-inducing heights.",
      "poster_path": "/v28T5F1IygM8vXWZIycfNEm3xcL.jpg",
      "media_type": "movie",
      "genre_ids": [
        53
      ],
      "popularity": 1251.586,
      "release_date": "2022-08-11",
      "video": false,
      "vote_average": 7.315,
      "vote_count": 92
    },
    {
      "adult": false,
      "backdrop_path": "/odJ4hx6g6vBt4lBWKFD1tI8WS4x.jpg",
      "id": 361743,
      "title": "Top Gun: Maverick",
      "original_language": "en",
      "original_title": "Top Gun: Maverick",
      "overview": "After more than thirty years of service as one of the Navy’s top aviators, and dodging the advancement in rank that would ground him, Pete “Maverick” Mitchell finds himself training a detachment of TOP GUN graduates for a specialized mission the likes of which no living pilot has ever seen.",
      "poster_path": "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
      "media_type": "movie",
      "genre_ids": [
        28,
        18
      ],
      "popularity": 2632.789,
      "release_date": "2022-05-24",
      "video": false,
      "vote_average": 8.357,
      "vote_count": 3424
    },
    {
      "adult": false,
      "backdrop_path": "/7Y3LdmsZukXhmwxtO0UF95BFBTt.jpg",
      "id": 682507,
      "title": "Where the Crawdads Sing",
      "original_language": "en",
      "original_title": "Where the Crawdads Sing",
      "overview": "Abandoned by her family, Kya raises herself all alone in the marshes outside of her small town. When her former boyfriend is found dead, Kya is instantly branded by the local townspeople and law enforcement as the prime suspect for his murder.",
      "poster_path": "/n1el846gLDXfhOvrRCsyvaAOQWv.jpg",
      "media_type": "movie",
      "genre_ids": [
        18,
        9648
      ],
      "popularity": 128.048,
      "release_date": "2022-07-15",
      "video": false,
      "vote_average": 7.3,
      "vote_count": 118
    },
    {
      "adult": false,
      "backdrop_path": "/xVbppM1xgbskOKgOuV8fbWBWHtt.jpg",
      "id": 762504,
      "title": "Nope",
      "original_language": "en",
      "original_title": "Nope",
      "overview": "Residents in a lonely gulch of inland California bear witness to an uncanny, chilling discovery.",
      "poster_path": "/AcKVlWaNVVVFQwro3nLXqPljcYA.jpg",
      "media_type": "movie",
      "genre_ids": [
        27,
        9648,
        878,
        53
      ],
      "popularity": 2735.725,
      "release_date": "2022-07-20",
      "video": false,
      "vote_average": 7.059,
      "vote_count": 1186
    },
    {
      "adult": false,
      "backdrop_path": "/jauI01vUIkPA0xVsamGj0Gs1nNL.jpg",
      "id": 507086,
      "title": "Jurassic World Dominion",
      "original_language": "en",
      "original_title": "Jurassic World Dominion",
      "overview": "Four years after Isla Nublar was destroyed, dinosaurs now live—and hunt—alongside humans all over the world. This fragile balance will reshape the future and determine, once and for all, whether human beings are to remain the apex predators on a planet they now share with history’s most fearsome creatures.",
      "poster_path": "/kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg",
      "media_type": "movie",
      "genre_ids": [
        12,
        28,
        878
      ],
      "popularity": 2558.729,
      "release_date": "2022-06-01",
      "video": false,
      "vote_average": 7.1,
      "vote_count": 3182
    },
    {
      "adult": false,
      "backdrop_path": "/14wIOjYCtfbO9EHTqCbiU9wncMz.jpg",
      "id": 852448,
      "title": "I Came By",
      "original_language": "en",
      "original_title": "I Came By",
      "overview": "A rebellious young graffiti artist, who targets the homes of the wealthy elite, discovers a shocking secret that leads him on a journey endangering himself and those closest to him.",
      "poster_path": "/856bLLUvEYu3dRDXCCoRE7oxO0V.jpg",
      "media_type": "movie",
      "genre_ids": [
        53
      ],
      "popularity": 90.286,
      "release_date": "2022-08-19",
      "video": false,
      "vote_average": 6.263,
      "vote_count": 95
    },
    {
      "adult": false,
      "backdrop_path": "/hYZ4a0JvPETdfgJJ9ZzyFufq8KQ.jpg",
      "id": 629176,
      "title": "Samaritan",
      "original_language": "en",
      "original_title": "Samaritan",
      "overview": "Thirteen year old Sam Cleary  suspects that his mysteriously reclusive neighbor Mr. Smith is actually the legendary vigilante Samaritan, who was reported dead 20 years ago. With crime on the rise and the city on the brink of chaos, Sam makes it his mission to coax his neighbor out of hiding to save the city from ruin.",
      "poster_path": "/vwq5iboxYoaSpOmEQrhq9tHicq7.jpg",
      "media_type": "movie",
      "genre_ids": [
        28,
        18,
        878
      ],
      "popularity": 6853.407,
      "release_date": "2022-08-25",
      "video": false,
      "vote_average": 7.059,
      "vote_count": 743
    },
    {
      "adult": false,
      "backdrop_path": "/skL7c4ZhZqo1IFbMcHNrol4fvkc.jpg",
      "id": 921360,
      "title": "Wire Room",
      "original_language": "en",
      "original_title": "Wire Room",
      "overview": "New recruit Justin Rosa must monitor arms-smuggling cartel member Eddie Flynn — and keep him alive at all costs. When a SWAT team descends on Flynn’s home, Rosa breaks protocol and contacts the gangster directly to save his life. As gunmen break into the Wire Room and chaos erupts, Mueller and Rosa make a final, desperate stand against the corrupt agents and officials who seek to destroy evidence and kill them both.",
      "poster_path": "/b9ykj4v8ykjRoGB7SpI1OuxblNU.jpg",
      "media_type": "movie",
      "genre_ids": [
        28,
        80,
        53
      ],
      "popularity": 109.69,
      "release_date": "2022-09-02",
      "video": false,
      "vote_average": 7.25,
      "vote_count": 8
    },
    {
      "adult": false,
      "backdrop_path": "/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg",
      "id": 438148,
      "title": "Minions: The Rise of Gru",
      "original_language": "en",
      "original_title": "Minions: The Rise of Gru",
      "overview": "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
      "poster_path": "/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg",
      "media_type": "movie",
      "genre_ids": [
        16,
        12,
        35,
        14
      ],
      "popularity": 2260.473,
      "release_date": "2022-06-29",
      "video": false,
      "vote_average": 7.682,
      "vote_count": 1814
    },
    {
      "adult": false,
      "backdrop_path": "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg",
      "id": 634649,
      "title": "Spider-Man: No Way Home",
      "original_language": "en",
      "original_title": "Spider-Man: No Way Home",
      "overview": "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
      "poster_path": "/uJYYizSuA9Y3DCs0qS4qWvHfZg4.jpg",
      "media_type": "movie",
      "genre_ids": [
        28,
        12,
        878
      ],
      "popularity": 1334.614,
      "release_date": "2021-12-15",
      "video": false,
      "vote_average": 8.044,
      "vote_count": 14984
    },
    {
      "adult": false,
      "backdrop_path": "/orV7mt3Gu4h2WEE2ki2CfeIkMuK.jpg",
      "id": 934756,
      "title": "Love in the Villa",
      "original_language": "en",
      "original_title": "Love in the Villa",
      "overview": "A young woman takes a trip to romantic Verona, Italy, after a breakup, only to find that the villa she reserved was double-booked, and she'll have to share her vacation with a cynical British man.",
      "poster_path": "/t92CXfEP5aWBATeOomcF0wF9NsM.jpg",
      "media_type": "movie",
      "genre_ids": [
        10749,
        35
      ],
      "popularity": 247.248,
      "release_date": "2022-09-01",
      "video": false,
      "vote_average": 6.065,
      "vote_count": 54
    },
    {
      "adult": false,
      "backdrop_path": "/xfNHRI2f5kHGvogxLd0C5sB90L7.jpg",
      "id": 539681,
      "title": "DC League of Super-Pets",
      "original_language": "en",
      "original_title": "DC League of Super-Pets",
      "overview": "When Superman and the rest of the Justice League are kidnapped, Krypto the Super-Dog must convince a rag-tag shelter pack - Ace the hound, PB the potbellied pig, Merton the turtle and Chip the squirrel - to master their own newfound powers and help him rescue the superheroes.",
      "poster_path": "/r7XifzvtezNt31ypvsmb6Oqxw49.jpg",
      "media_type": "movie",
      "genre_ids": [
        16,
        28,
        10751,
        878,
        35
      ],
      "popularity": 3764.786,
      "release_date": "2022-07-27",
      "video": false,
      "vote_average": 7.639,
      "vote_count": 400
    },
    {
      "adult": false,
      "backdrop_path": "/xyS0NgcQ2pbGWcwvSSu3xKEBhoO.jpg",
      "id": 614934,
      "title": "Elvis",
      "original_language": "en",
      "original_title": "Elvis",
      "overview": "The life story of Elvis Presley as seen through the complicated relationship with his enigmatic manager, Colonel Tom Parker.",
      "poster_path": "/qBOKWqAFbveZ4ryjJJwbie6tXkQ.jpg",
      "media_type": "movie",
      "genre_ids": [
        10402,
        18,
        36
      ],
      "popularity": 851.147,
      "release_date": "2022-06-22",
      "video": false,
      "vote_average": 7.9,
      "vote_count": 1333
    },
    {
      "adult": false,
      "backdrop_path": "/wcKFYIiVDvRURrzglV9kGu7fpfY.jpg",
      "id": 453395,
      "title": "Doctor Strange in the Multiverse of Madness",
      "original_language": "en",
      "original_title": "Doctor Strange in the Multiverse of Madness",
      "overview": "Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.",
      "poster_path": "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
      "media_type": "movie",
      "genre_ids": [
        14,
        28,
        12
      ],
      "popularity": 1418.685,
      "release_date": "2022-05-04",
      "video": false,
      "vote_average": 7.49,
      "vote_count": 5597
    },
    {
      "adult": false,
      "backdrop_path": "/aLDB5oqKDgzgED6YDsKeh0hCaVq.jpg",
      "id": 879080,
      "title": "The Festival of Troubadours",
      "original_language": "tr",
      "original_title": "Âşıklar Bayramı",
      "overview": "An unexpected reunion between a traveling musician and his son opens old wounds as the two set out on a long journey to a troubadour festival.",
      "poster_path": "/hcIhpCOj4JdvWMyaXUDXOqf7Opj.jpg",
      "media_type": "movie",
      "genre_ids": [
        18
      ],
      "popularity": 105.281,
      "release_date": "2022-09-02",
      "video": false,
      "vote_average": 6,
      "vote_count": 4
    },
    {
      "adult": false,
      "backdrop_path": "/ugS5FVfCI3RV0ZwZtBV3HAV75OX.jpg",
      "id": 610150,
      "title": "Dragon Ball Super: Super Hero",
      "original_language": "ja",
      "original_title": "ドラゴンボール超 スーパーヒーロー",
      "overview": "The Red Ribbon Army, an evil organization that was once destroyed by Goku in the past, has been reformed by a group of people who have created new and mightier Androids, Gamma 1 and Gamma 2, and seek vengeance against Goku and his family.",
      "poster_path": "/rugyJdeoJm7cSJL1q4jBpTNbxyU.jpg",
      "media_type": "movie",
      "genre_ids": [
        16,
        878,
        28
      ],
      "popularity": 12401.679,
      "release_date": "2022-06-11",
      "video": false,
      "vote_average": 8.019,
      "vote_count": 1168
    },
    {
      "adult": false,
      "backdrop_path": "/uP9GKijTJk1PS0UWO65kcLKOzL1.jpg",
      "id": 747803,
      "title": "One Way",
      "original_language": "en",
      "original_title": "One Way",
      "overview": "On the run with a bag full of cash after a robbing his former crime boss—and a potentially fatal wound—Freddy slips onto a bus headed into the unrelenting California desert. With his life slipping through his fingers, Freddy is left with very few choices to survive.",
      "poster_path": "/la6BUTGhRHumNXiEGi9A2if6cZd.jpg",
      "media_type": "movie",
      "genre_ids": [
        80,
        28,
        53
      ],
      "popularity": 59.1,
      "release_date": "2022-09-02",
      "video": false,
      "vote_average": 4.5,
      "vote_count": 4
    },
    {
      "adult": false,
      "backdrop_path": "/vvObT0eIWGlArLQx3K5wZ0uT812.jpg",
      "id": 616037,
      "title": "Thor: Love and Thunder",
      "original_language": "en",
      "original_title": "Thor: Love and Thunder",
      "overview": "After his retirement is interrupted by Gorr the God Butcher, a galactic killer who seeks the extinction of the gods, Thor Odinson enlists the help of King Valkyrie, Korg, and ex-girlfriend Jane Foster, who now inexplicably wields Mjolnir as the Relatively Mighty Girl Thor. Together they embark upon a harrowing cosmic adventure to uncover the mystery of the God Butcher’s vengeance and stop him before it’s too late.",
      "poster_path": "/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg",
      "media_type": "movie",
      "genre_ids": [
        28,
        12,
        14
      ],
      "popularity": 4630.653,
      "release_date": "2022-07-06",
      "video": false,
      "vote_average": 6.738,
      "vote_count": 2245
    },
    {
      "adult": false,
      "backdrop_path": "/5FFpcmPzD5mhLom7bymZq7Py8eT.jpg",
      "id": 862551,
      "title": "Me Time",
      "original_language": "en",
      "original_title": "Me Time",
      "overview": "With his family away, a devoted stay-at-home dad enjoys his first me time in years by joining his hard-partying old friend on a wild birthday adventure.",
      "poster_path": "/6y51Tmid49SeNSfN4o1op2hjnkI.jpg",
      "media_type": "movie",
      "genre_ids": [
        35
      ],
      "popularity": 1457.745,
      "release_date": "2022-08-26",
      "video": false,
      "vote_average": 5.71,
      "vote_count": 231
    },
    {
      "adult": false,
      "backdrop_path": "/o0ITRbEUcbYnDxACU7tSWWmnDXq.jpg",
      "id": 869626,
      "title": "Marcel the Shell with Shoes On",
      "original_language": "en",
      "original_title": "Marcel the Shell with Shoes On",
      "overview": "Marcel is an adorable one-inch-tall shell who ekes out a colorful existence with his grandmother Connie and their pet lint, Alan. Once part of a sprawling community of shells, they now live alone as the sole survivors of a mysterious tragedy. But when a documentary filmmaker discovers them amongst the clutter of his Airbnb, the short film he posts online brings Marcel millions of passionate fans, as well as unprecedented dangers and a new hope at finding his long-lost family.",
      "poster_path": "/jaYmP4Ct8YLnxWAW2oYkUjeXtzm.jpg",
      "media_type": "movie",
      "genre_ids": [
        16,
        35,
        10751,
        18
      ],
      "popularity": 22.32,
      "release_date": "2022-06-24",
      "video": false,
      "vote_average": 8,
      "vote_count": 26
    }
  ],
  "total_pages": 1000,
  "total_results": 20000
}
"""
package tv.brid.data.repositories

import kotlinx.coroutines.delay
import tv.brid.data.youtube.YoutubeExtractor
import tv.brid.domain.VideosRepository
import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.Video
import javax.inject.Inject

class FakeYoutubeRepositoryImpl @Inject constructor(
    private val youtubeExtractor: YoutubeExtractor
) : VideosRepository {

    private val listOfVideoIds = listOf(
        "i4x15dkoZsE",
        "R7ew_apl2Cc",
        "lc63T2temZo",
        "jgZCroKm838",
        "YM2rt6-OKdQ",
        "eUErLvfGus8",
        "Hgl77ZdyxvE",
        "2f7jSwjxHkQ",
        "DT5oYUAIZNU",
        "b9dyV7XUfCA",
        "eR8FI0kcedA",
        "I08sN8_dp_k",
        "P6rmDBNVmLA",
        "0gvhf0afclM",
        "pdCFHwf_vgg",
        "OZvrL_lWeao",
        "bor0Nuv90GY",
        "OUsW8htdNTg",
        "1VeaUHDowfA",
        "yl0sUaMxYHA"
    )

    private val listOfTitles = listOf(
        "ZADRUGA UZIVO - ELITA",
        "Fall Asleep In Less Than 3 Minutes ★ Stress And Anxiety Relief ★ Goodbye Insomnia",
        "Will Colleges Reject You for a Typo?",
        "Tijana Bogicevic - Blizu (Official Video)",
        "HENNY X BRESKVICA - KO TO TAMO (OFFICIAL VIDEO) Prod. By Jhinsen",
        "Tijana Bogicevic x Sara Milutinovic - Sirina (Official Video)",
        "Battle of Grunwald, 1410 ⚔️ The Downfall of the Teutonic Order ⚔️ DOCUMENTARY",
        "Tea Tairovic - Malo mi je (Official video)",
        "Tea Tairovic - Prezivecu (Official Video)",
        "Battle for Survival ⚔️ How did Alexios Komnenos save the Byzantine Empire? DOCUMENTARY",
        "Tea Tairović - Izrael i Palestina (Official Video | Album Balerina)",
        "Battle of Kosovo, 1389 ⚔️ The Last stand of the Christians against Ottoman expansion ⚔️ DOCUMENTARY",
        "SLOBA RADANOVIC - AKO NEKO PITA (OFFICIAL VIDEO) 4K",
        "Gibanica - National Dish of Serbia (Day 17 / 195)",
        "Tea Tairovic - Na Jednu Noc (Official Video)",
        "Tea Tairovic - Boy Boy (Official Video)",
        "TEA TAIROVIC -  HAJDE (OFFICIAL VIDE0 2021)",
        "Ilma Karahmet - Ništa tvoje (Official Video 2019)",
        "Waterloo, 1815 ⚔️ The Truth behind Napoleon&#39;s final defeat",
        "Tea Tairovic - Dva i dva (Official video)"
    )

    private val listOfDesc = listOf(
        "Zadruga 7 je Rijaliti šou koji prati grupa poznatih takmičara,najrazlićitijeg profila lićnosti,koji žive zajedno izolovani od spoljnog ...",
        "Fall Asleep In Less Than 3 Minutes ☆ Stress And Anxiety Relief ☆ Goodbye Insomnia Channel: Weightless Sleep Meditation ...",
        "I'll edit your college essay! https://nextadmit.com.",
        "Connect with TIJANA BOGICEVIC on: Facebook: http://fb.me/tijanabogicevicmusic Instagram: ...",
        "Generacija Zed ❤️ Kontakt: zedgeneracija@gmail.com Instagram: @generacija_zed Muzika: Henny Tekst: Relja Torinno ...",
        "Slušajte na streaming servisima: https://fanlink.to/cpa9 Pesma \"Širina\" je prva stvar koju je Tijana snimila posle uspešnog albuma ...",
        "Go to http://bit.ly/thld_cs_historymarche and use code HISTORYMARCHE to save 25% off today. Thanks to Curiosity Stream for ...",
        "Label & copyright: Tea Tairovic & T Music, ℗ & © 2022. All rights reserved. Booking & Info: Nikola Bridžis +381 65 8158 598 ...",
        "Label & copyright: Tea Tairovic & T Music, ℗ & © 2022. All rights reserved. Booking & Info: Nikola Bridžis +381 65 8158 598 ...",
        "Sign up on HistoryHit and get 50% off your first 3 months by using the code HISTORYMARCHE ...",
        "Label & copyright: Tea Tairovic & T Music, ℗ & © 2023. All rights reserved. Streaming: https://bfan.link/albumbalerina 17.6.2023.",
        "With the decline of the Byzantine Empire, the Bulgarian and Serbian states were gradually losing their power, while the young ...",
        "BOOKING & INFO: Goran Lečić - Infinity production TEL: +38166404050 iTunes: ...",
        "So crispy and simple to make. Absolute crowd pleaser and I'm so excited to make it again Ingredients Pastry - 450g filo pastry (1 ...",
        "TeaTairovic #NaJednuNoc Label & copyright: T Music, ℗ & © 2021. All rights reserved. Booking & Info: Nikola Bridžis +381 65 ...",
        "Label & copyright: Tea Tairovic & T Music, ℗ & © 2022. All rights reserved. Booking & Info: Nikola Bridžis +381 65 8158 598 ...",
        "TeaTairovic #Hajde Label & copyright: T Music, ℗ & © 2021. All rights reserved. Booking & Info: Nikola Bridžis +381 65 8158 598 ...",
        "Booking: +38762453020 ✓ Stream & Download: https://emdc.yt/nistatvoje ✓ Social Media: https://emdc.yt/ilma ...",
        "Go to bit.ly/thld_cs_historymarche and use code HISTORYMARCHE to save 25% off today. Thanks to Curiosity Stream for ...",
        "Label & copyright: Tea Tairovic & T Music, ℗ & © 2022. All rights reserved. Booking & Info: Nikola Bridžis +381 65 8158 598 ..."
    )

    private val listOfThumbnails = listOf(
        "https://i.ytimg.com/vi/i4x15dkoZsE/mqdefault_live.jpg",
        "https://i.ytimg.com/vi/R7ew_apl2Cc/mqdefault_live.jpg",
        "https://i.ytimg.com/vi/lc63T2temZo/mqdefault.jpg",
        "https://i.ytimg.com/vi/jgZCroKm838/mqdefault.jpg",
        "https://i.ytimg.com/vi/YM2rt6-OKdQ/mqdefault.jpg",
        "https://i.ytimg.com/vi/eUErLvfGus8/mqdefault.jpg",
        "https://i.ytimg.com/vi/Hgl77ZdyxvE/mqdefault.jpg",
        "https://i.ytimg.com/vi/2f7jSwjxHkQ/mqdefault.jpg",
        "https://i.ytimg.com/vi/DT5oYUAIZNU/mqdefault.jpg",
        "https://i.ytimg.com/vi/b9dyV7XUfCA/mqdefault.jpg",
        "https://i.ytimg.com/vi/eR8FI0kcedA/mqdefault.jpg",
        "https://i.ytimg.com/vi/I08sN8_dp_k/mqdefault.jpg",
        "https://i.ytimg.com/vi/P6rmDBNVmLA/mqdefault.jpg",
        "https://i.ytimg.com/vi/0gvhf0afclM/mqdefault.jpg",
        "https://i.ytimg.com/vi/pdCFHwf_vgg/mqdefault.jpg",
        "https://i.ytimg.com/vi/OZvrL_lWeao/mqdefault.jpg",
        "https://i.ytimg.com/vi/bor0Nuv90GY/mqdefault.jpg",
        "https://i.ytimg.com/vi/OUsW8htdNTg/mqdefault.jpg",
        "https://i.ytimg.com/vi/1VeaUHDowfA/mqdefault.jpg",
        "https://i.ytimg.com/vi/yl0sUaMxYHA/mqdefault.jpg"
    )

    override suspend fun getVideos(pageToken: String?): SearchResponse {
        val arrOfVideos = ArrayList<Video>()
        var nextPageToken: String? = null
        var prevPageToken: String? = null

        when (pageToken) {
            "1" -> nextPageToken = "2"
            "2" -> prevPageToken = "1"
            else -> nextPageToken = "2"
        }

        for (i in 0..19) {
            arrOfVideos.add(
                Video(
                    id = listOfVideoIds[i % listOfVideoIds.size],
                    title = listOfTitles[i % listOfTitles.size],
                    description = listOfDesc[i % listOfDesc.size],
                    thumbnailUrl = listOfThumbnails[i % listOfThumbnails.size],
                    sourceUrl = null
                )
            )
        }
        delay(2000)
        return SearchResponse(nextPageToken, prevPageToken, arrOfVideos)
    }

    override suspend fun getVideo(id: String): Video? =
        getVideos(null).data
            .find { it.id == id }
            ?.copy(sourceUrl = youtubeExtractor.getVideoUrl(id))
}
package donets.danylo.natife.testtask.DataServices

import donets.danylo.natife.testtask.Data.DataResult
import retrofit2.http.GET

interface DataService {
    @GET("https://api.giphy.com/v1/gifs/trending?api_key=n9ypJo2caEm2BmUEBFOzCDkHjy3Xeol1&q=&limit=25&offset=0&rating=g&lang=en")

    fun getGifs() : retrofit2.Call<DataResult>
}
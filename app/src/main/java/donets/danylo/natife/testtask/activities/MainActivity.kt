package donets.danylo.natife.testtask.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import donets.danylo.natife.testtask.Adapter.GifsAdapter
import donets.danylo.natife.testtask.Data.DataObject
import donets.danylo.natife.testtask.Data.DataResult
import donets.danylo.natife.testtask.DataServices.DataService
import donets.danylo.natife.testtask.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.giphy.com/v1/"
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val gifs = mutableListOf<DataObject>()
        val adapter = GifsAdapter(this, gifs)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        //recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter



        adapter.setOnItemClickListener(object : GifsAdapter.OnItemClickListener{
            override fun onItenClick(position: Int) {
                val intent = Intent(this@MainActivity, FullScreenView::class.java)
                intent.putExtra("url", gifs[position].images.ogImage.url)
                startActivity(intent)
            }

        })


        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val retroService = retrofit.create(DataService::class.java)
        retroService.getGifs().enqueue(object : Callback<DataResult?> {

            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()
                if(body ==null){
                    Log.d(TAG, "onResponce: No responce")
                }

                gifs.addAll(body?.res ?: emptyList())
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                result("Something wrong")
            }
        })
    }

    private fun result(title: String)
    {
        val message = "\nBad internet connection. Try retry"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Retry")
            { _,_ ->
                finish();
                startActivity(getIntent());
            }
            .setCancelable(false)
            .show()
    }




    override fun onBackPressed() {
        finish()
        super.onBackPressed()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}


package com.app.shanindu.news.ui


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.app.shanindu.news.R
import com.app.shanindu.news.adapter.NewsAdapter
import com.app.shanindu.news.helper.InternetObserver
import com.app.shanindu.news.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class CustomFragment : Fragment() {
    private val TAG = "CustomFragment"

    private val BASE_URL = "https://newsapi.org/v2/everything?q="
    private val KEY = "&apiKey=7e34fe9b5e32418ebc3f42370f1458f3"

    private var recyclerView: RecyclerView? = null
    private var btnRetry: Button? = null
    //    private var btnChicp: Button? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: NewsAdapter? = null
    private val newsList = ArrayList<Article>()
    private var lyt_progress: LinearLayout? = null
    private var lyt_connection: LinearLayout? = null
    @Volatile
    private var internetAvailability: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_custom, container, false)


        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        lyt_progress = view.findViewById<LinearLayout>(R.id.lyt_progress)
        lyt_connection = view.findViewById<LinearLayout>(R.id.lyt_connection)
        btnRetry = view.findViewById<Button>(R.id.btn_retry)

        val btnBitcoin = view.findViewById<Button>(R.id.btn_bitcoin)
        val btnApple = view.findViewById<Button>(R.id.btn_apple)
        val btnEarthquake = view.findViewById<Button>(R.id.btn_earthquake)
        val btnAnimal = view.findViewById<Button>(R.id.btn_animal)

        initComponent()

        btnBitcoin.setBackgroundResource(R.drawable.btn_chip_enable)
        btnApple.setBackgroundResource(R.drawable.btn_chip_disable)
        btnEarthquake.setBackgroundResource(R.drawable.btn_chip_disable)
        btnAnimal.setBackgroundResource(R.drawable.btn_chip_disable)

        btnBitcoin.setOnClickListener {
            // your code to perform when the user clicks on the button
            fetchCustomData("bitcoin")
            btnBitcoin.setBackgroundResource(R.drawable.btn_chip_enable)
            btnApple.setBackgroundResource(R.drawable.btn_chip_disable)
            btnEarthquake.setBackgroundResource(R.drawable.btn_chip_disable)
            btnAnimal.setBackgroundResource(R.drawable.btn_chip_disable)

        }
        btnApple.setOnClickListener {
            // your code to perform when the user clicks on the button
            fetchCustomData("apple")
            btnBitcoin.setBackgroundResource(R.drawable.btn_chip_disable)
            btnApple.setBackgroundResource(R.drawable.btn_chip_enable)
            btnEarthquake.setBackgroundResource(R.drawable.btn_chip_disable)
            btnAnimal.setBackgroundResource(R.drawable.btn_chip_disable)

        }
        btnEarthquake.setOnClickListener {
            // your code to perform when the user clicks on the button
            fetchCustomData("earthquake")
            btnBitcoin.setBackgroundResource(R.drawable.btn_chip_disable)
            btnApple.setBackgroundResource(R.drawable.btn_chip_disable)
            btnEarthquake.setBackgroundResource(R.drawable.btn_chip_enable)
            btnAnimal.setBackgroundResource(R.drawable.btn_chip_disable)

        }
        btnAnimal.setOnClickListener {
            // your code to perform when the user clicks on the button
            fetchCustomData("animal")
            btnBitcoin.setBackgroundResource(R.drawable.btn_chip_disable)
            btnApple.setBackgroundResource(R.drawable.btn_chip_disable)
            btnEarthquake.setBackgroundResource(R.drawable.btn_chip_disable)
            btnAnimal.setBackgroundResource(R.drawable.btn_chip_enable)

        }


        // Inflate the layout for this fragment
        return view
    }

    private fun initComponent() {

        // in content do not change the layout size of the RecyclerView
        recyclerView?.setHasFixedSize(true)

        // use a linear layout manager
        layoutManager = LinearLayoutManager(context)
        recyclerView?.setLayoutManager(layoutManager)

        // specify an adapter
        mAdapter = NewsAdapter(context, newsList)
        recyclerView?.setAdapter(mAdapter)

        fetchCustomData("bitcoin")


        mAdapter?.SetOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, obj: Article) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("article", obj)
                startActivity(intent)

            }
        })
    }

    //    //fetching custom news from API
    private fun fetchCustomData(keyword: String) {

//        lyt_progress.setVisibility(View.VISIBLE)
//        lyt_progress.setAlpha(1.0f)
//        recyclerView.setVisibility(View.GONE)


        internetAvailability = InternetObserver.isConnectedToInternet(context)
        if (internetAvailability) {
// Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(context)
            val request = object :
                    StringRequest(
                            Request.Method.GET,
                            BASE_URL + keyword + KEY,
                            object : Response.Listener<String> {
                                override fun onResponse(response: String) {
                                    Log.d("response", "" + response)
                                    if (response == "") {
                                        return
                                    }
                                    try {

                                        val jObj = JSONObject(response)
                                        val news: List<Article>

                                        news = Gson().fromJson<List<Article>>(
                                                jObj.getJSONArray("articles").toString(),
                                                object : TypeToken<List<Article>>() {

                                                }.type
                                        )

                                        // adding users to user list
                                        newsList.clear()
                                        newsList.addAll(news)

                                        // refreshing recycler view
                                        mAdapter?.notifyDataSetChanged()

                                        lyt_progress?.setVisibility(View.GONE)
                                        recyclerView?.setAlpha(1.0f)
                                        recyclerView?.setVisibility(View.VISIBLE)


                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }


                                }
                            },
                            object : Response.ErrorListener {
                                override fun onErrorResponse(error: VolleyError) {
                                    // error in getting json
                                    Log.e(TAG, "Error: " + error.message)


                                }
                            }) {


            }
            request.setRetryPolicy(
                    DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            )
            queue.add(request)

        } else {

            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show()


        }
    }


    override fun onDetach() {
        super.onDetach()

    }
}// Required empty public constructor

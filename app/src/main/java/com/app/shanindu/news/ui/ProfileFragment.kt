package com.app.shanindu.news.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.app.shanindu.news.R
import com.app.shanindu.news.model.User
import com.app.shanindu.news.model.UserDao


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private val TAG = "ProfileFragment"

    private val dao = UserDao();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnRegister = view.findViewById<Button>(R.id.btn_register);
        val btnLogout = view.findViewById<Button>(R.id.btn_logout);
        val edtFname = view.findViewById<EditText>(R.id.txt_firstname);
        val edtLname = view.findViewById<EditText>(R.id.txt_lastname);
        val edtUsername = view.findViewById<EditText>(R.id.txt_username);

        val txtName = view.findViewById<TextView>(R.id.txt_name);

        val layoutRegister = view.findViewById<LinearLayout>(R.id.layout_register);
        val layoutInfo = view.findViewById<LinearLayout>(R.id.layout_info);

        layoutRegister.visibility = View.VISIBLE;
        layoutInfo.visibility = View.GONE;



        btnRegister.setOnClickListener {

            if (edtFname.text.toString().equals("")) {
                edtFname.setError("Invalid first name")

            } else if (edtLname.text.toString().equals("")) {
                edtLname.setError("Invalid last name")

            } else if (edtUsername.text.toString().equals("")) {
                edtUsername.setError("Invalid username")

            } else {
                btnRegister.setText("REGISTERING...")
                try {
                    dao.add(User(1, edtFname.text.toString().trim(), edtLname.text.toString().trim(), edtUsername.text.toString().trim()))
                    Toast.makeText(context, "Successfully registered!", Toast.LENGTH_LONG).show()

                    edtFname.setText("")
                    edtLname.setText("")
                    edtUsername.setText("")

                    btnRegister.setText("REGISTERED")

                    layoutRegister.visibility = View.GONE;
                    layoutInfo.visibility = View.VISIBLE;


                    txtName.setText("Hi, " + dao.getUser().firstName + " " + dao.getUser().lastName)


                } catch (ex: Exception) {
                    btnRegister.setText("REGISTER")
                    if (context != null) {
                        Log.e(TAG, ex.localizedMessage.toString())

                    }
                }


            }


        }

        btnLogout.setOnClickListener {
            try {
                dao.removeAll()
                layoutRegister.visibility = View.VISIBLE;
                layoutInfo.visibility = View.GONE;
            } catch (ex: Exception) {
                if (context != null) {
                    Log.e(TAG, ex.localizedMessage.toString())
                }
            }
        }


        // Inflate the layout for this fragment
        return view
    }


}// Required empty public constructor

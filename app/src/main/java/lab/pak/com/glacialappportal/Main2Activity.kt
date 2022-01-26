package lab.pak.com.glacialappportal

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.adminpanel.*
import kotlinx.android.synthetic.main.adminpanel.header
import kotlinx.android.synthetic.main.adminpanel.orderbut

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminpanel)
         setSupportActionBar(header)


        taskbut.setOnClickListener{

        }

        orderbut.setOnClickListener {

        }
        }


}

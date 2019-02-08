package com.coe.myapplication


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.coe.myapplication.utility.LinePayViewModel
import android.graphics.Bitmap
import android.widget.Button
import net.glxn.qrgen.android.QRCode


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LinePayFragment : Fragment() {


  var bitmap: Bitmap? = null
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment

    val v : View = inflater.inflate(R.layout.fragment_line_pay, container, false)

    var txtOrderID = v.findViewById(R.id.txtOrderID) as TextView
    var editText = v.findViewById(R.id.editAmount) as EditText
    var iv = v.findViewById(R.id.iv) as ImageView
    var btnLinePay = v.findViewById(R.id.btnLinePay) as Button

    var model =ViewModelProviders.of(this.activity!!).get(LinePayViewModel::class.java)



    model.getResponseLinePay().observe(this, Observer {
      bitmap = QRCode.from(it!!.info.paymentUrl.web).bitmap();
      iv.setImageBitmap(bitmap);
    })


    btnLinePay.setOnClickListener {
      var orderID = (2001 until 999999).random();

      var amount = editText.text.toString().toInt();

      txtOrderID.text = "Order ID :"+ orderID
      model.requestLinePay(orderID.toString() ,amount )
    }


    return v;
  }


}

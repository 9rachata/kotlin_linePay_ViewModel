package com.coe.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponseLinePay(
    @SerializedName("info")
    val info: Info,
    @SerializedName("returnCode")
    val returnCode: String,
    @SerializedName("returnMessage")
    val returnMessage: String
) {
    data class Info(
        @SerializedName("paymentAccessToken")
        val paymentAccessToken: String,
        @SerializedName("paymentUrl")
        val paymentUrl: PaymentUrl,
        @SerializedName("transactionId")
        val transactionId: Long
    ) {
        data class PaymentUrl(
            @SerializedName("app")
            val app: String,
            @SerializedName("web")
            val web: String
        )
    }
}
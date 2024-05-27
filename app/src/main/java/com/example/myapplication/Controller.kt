package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller: ViewModel() {

    var dataViewModel=MutableLiveData<JsonElement>()
    var userNameVM=MutableLiveData<String>()
    var userRoleVM=MutableLiveData<String>()


    private val apiService: APIService =
        RetrofitClient.get()!!.create(APIService::class.java)

    fun callAPI(userName:String,password:String,imei:String,subscriberId:String,appVersion:String,deviceName:String){

        val content=Contents(userName, password, imei, subscriberId, appVersion, deviceName)
       apiService.signIn(DataRequest(content)).enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val jsonElement = response.body()
                    Log.d("DEBUG","body:$jsonElement")
                    if (jsonElement != null && jsonElement.isJsonObject) {
                        val jsonObject = jsonElement.asJsonObject
                        val contents = jsonObject.getAsJsonObject("contents")
                        val userInfo = contents.getAsJsonObject("userInfo")
                        val username = userInfo.get("username").asString
                        val userRole= userInfo.getAsJsonObject("userRole")
                        val userRoleName=userRole.get("name").asString

                        dataViewModel.postValue(jsonElement!!)
                        userNameVM.postValue(userName)
                        userRoleVM.postValue(userRoleName)

                    } else {
                        Log.e("DEBUG", "Invalid JSON response")
                    }
                } else {
                    Log.e("DEBUG", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e("DEBUG", "Call api failed: ${t.message}")
            }
        })
    }
}
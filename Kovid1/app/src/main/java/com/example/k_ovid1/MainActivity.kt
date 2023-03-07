package com.example.k_ovid1

import android.icu.lang.UCharacter.toString
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var data : JSONArray
    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Jsonparse()
    }

    fun Jsonparse() {
        var url = URL("https://api.odcloud.kr/api/apnmOrg/v1/list?page=1&perPage=10&serviceKey=9PAc6aMn2DC3xdA7rYZn71Hxr3mT9V5E4qnnakQkwj44zVNrPfV%2FVLVnDsnf30wrZZ%2BD%2FS%2BWRTNinP7J8lMjeQ%3D%3D")
            var conn = url.openConnection()
            var input = conn.getInputStream()
            var isr = InputStreamReader(input)
            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            var br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String?
            var buf = StringBuffer()

            do{
                str = br.readLine()

                if(str!=null){
                    buf.append(str)
                }
            }while (str!=null)

            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            var root = JSONObject(buf.toString())
            // 화면에 출력
            runOnUiThread {
                // 객체 안에 있는 data 이름의 리스트를 가져옴
                data = root.getJSONArray("data")
                var obj: JSONObject
                // 리스트에 있는 데이터를 data.length 만큼 가져옴
                for(i in 0..data.length()-1){
                    obj = data.getJSONObject(i)

                    name = obj.getString("orgnm")
                    Log.d("name", name)

                }
            }
        }
}
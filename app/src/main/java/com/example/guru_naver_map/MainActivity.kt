package com.example.guru_naver_map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.UiSettings
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.LocationButtonView


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val TAG = "MainActivity"
    private val PERMISSION_REQUEST_CODE = 100
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private var departureMarker: Marker? = null
    private var destinationMarker: Marker? = null
    private var pathOverlay: PathOverlay? = null
    private var isMarkersVisible = false //Marker들의 가시성 여부
    private val markers = mutableListOf<Marker>() // List to store all the markers

    private lateinit var mLocationSource: FusedLocationSource
    private lateinit var mNaverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 지도 객체 생성
        val fm: FragmentManager = supportFragmentManager
        var mapFragment: MapFragment? = fm.findFragmentById(R.id.map_fragment) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit()
        }
        // onMapReady에서 NaverMap 객체를 받음
        mapFragment?.getMapAsync(this)

        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = FusedLocationSource(this, PERMISSION_REQUEST_CODE)

        // "가까운 무더위쉼터 안내" 버튼을 ID
        val buttonNavigateToMarkers = findViewById<Button>(R.id.button2)
        // "가까운 무더위쉼터 안내" 버튼에 클릭 리스너를 설정
        buttonNavigateToMarkers.setOnClickListener {
            // Marker들의 가시성 상태를 반전
            isMarkersVisible = !isMarkersVisible
            if (isMarkersVisible) {
                showMarkers() // Marker들을 표시하는 함수 호출
            } else {
                hideMarkers() // Marker들을 숨기는 함수 호출
            }
        }
    }

    //마커&경로
    override fun onMapReady(naverMap: NaverMap) {
        Log.d(TAG, "onMapReady")
        //마커 표시

        val marker1 = Marker()
        val marker2 = Marker()
        val marker3 = Marker()
        val marker4 = Marker()
        val marker5 = Marker()
        val marker6 = Marker()
        val marker7 = Marker()
        val marker8 = Marker()
        val marker9 = Marker()
        val marker10 = Marker()

        marker1.position = LatLng(37.629336, 127.069615) //공릉종합사회복지관
        marker1.map = naverMap
        marker1.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity1::class.java)
            startActivity(intent)
            true
        }
        marker2.position = LatLng(37.624278, 127.050753) //월계문화복지센터
        marker2.map = naverMap
        marker2.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
            true
        }
        marker3.position = LatLng(37.628414, 127.052153) //월계종합사회복지관
        marker3.map = naverMap
        marker3.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(intent)
            true
        }
        marker4.position = LatLng(37.630165, 127.060023) //노원1종합사회복지관
        marker4.map = naverMap
        marker4.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity4::class.java)
            startActivity(intent)
            true
        }
        marker5.position = LatLng(37.656628, 127.075864) //중계종합사회복지관
        marker5.map = naverMap
        marker5.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity5::class.java)
            startActivity(intent)
            true
        }
        marker6.position = LatLng(37.646548, 127.069507) //평화종합사회복지관
        marker6.map = naverMap
        marker6.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity6::class.java)
            startActivity(intent)
            true
        }
        marker7.position = LatLng(37.654110, 127.056732) //노원구청 1층
        marker7.map = naverMap
        marker7.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity7::class.java)
            startActivity(intent)
            true
        }
        marker8.position = LatLng(37.6552, 127.0594) //노블레스 관광호텔
        marker8.map = naverMap
        marker8.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity8::class.java)
            startActivity(intent)
            true
        }
        marker9.position = LatLng(37.624792, 127.073839) //공릉1동주민센터
        marker9.map = naverMap
        marker9.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity9::class.java)
            startActivity(intent)
            true
        }
        marker10.position = LatLng(37.621289, 127.083385) //공릉2동주민센터
        marker10.map = naverMap
        marker10.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity10::class.java)
            startActivity(intent)
            true
        }
        markers.add(marker1)
        markers.add(marker2)
        markers.add(marker3)
        markers.add(marker4)
        markers.add(marker5)
        markers.add(marker6)
        markers.add(marker7)
        markers.add(marker8)
        markers.add(marker9)
        markers.add(marker10)

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap
        mNaverMap.locationSource = mLocationSource
        // 권한 확인. 결과는 onRequestPermissionsResult 콜백 메서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)

        //UI설정 현재위치 버튼
        val uiSettings: UiSettings = mNaverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        val locationButtonView: LocationButtonView  = findViewById(R.id.location)
        locationButtonView.map = mNaverMap
        locationButtonView.visibility = View.GONE

        hideMarkers()
        //마커 가시성 설정
        val buttonNavigateToMarkers = findViewById<Button>(R.id.button2)
        buttonNavigateToMarkers.setOnClickListener {
            // Marker들의 가시성 상태를 반전
            isMarkersVisible = !isMarkersVisible
            if (isMarkersVisible) {
                showMarkers() // Marker들을 표시하는 함수 호출
            } else {
                hideMarkers() // Marker들을 숨기는 함수 호출
            }
        }
        naverMap.setOnMapClickListener { point, coord ->
            if (departureMarker == null) {
                departureMarker = Marker()
                departureMarker!!.position = coord
                departureMarker!!.map = naverMap
            } else if (destinationMarker == null) {
                destinationMarker = Marker()
                destinationMarker!!.position = coord
                destinationMarker!!.map = naverMap

                // 경로 표시
                showPath()

            } else {
                // 마커가 이미 두 개 이상 설정된 경우, 새로운 출발지로 설정
                departureMarker!!.position = coord
                destinationMarker!!.map = null
                destinationMarker = null
                pathOverlay!!.map = null
                Toast.makeText(this, "출발지를 다시 설정했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        // 마커 클릭 이벤트 리스너 설정
        marker1.setOnClickListener {
            //다른 Activity로 이동하는 코드
            val intent = Intent(this@MainActivity, MainActivity1::class.java)
            startActivity(intent)
            true // 이벤트가 소비되었음을 반환
        }
    }

    //경로안내
    private fun showPath() {
        departureMarker?.let { departure ->
            destinationMarker?.let { destination ->
                val pathPoints = listOf(departure.position, destination.position)
                pathOverlay = PathOverlay()
                pathOverlay!!.coords = pathPoints
                pathOverlay!!.map = departure.map

                //출발지와 도착지 사이의 화면 표시 영역으로 이동
                val cameraUpdate = CameraUpdate.scrollTo(
                    LatLng(
                        (departure.position.latitude + destination.position.latitude) / 2,
                        (departure.position.longitude + destination.position.longitude) / 2
                    )
                )
                departure.map?.moveCamera(cameraUpdate)
            }
        }
    }
    // "가까운 무더위쉼터 안내" 버튼을 눌렀을 때 Marker들을 표시하는 함수
    private fun showMarkers() {
        for (marker in markers) {
            marker.map = mNaverMap
        }
    }
    // Marker들을 숨기는 함수
    private fun hideMarkers() {
        for (marker in markers) {
            marker.map = null
        }
    }
    //권한
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // request code와 권한 획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
        }
    }
}

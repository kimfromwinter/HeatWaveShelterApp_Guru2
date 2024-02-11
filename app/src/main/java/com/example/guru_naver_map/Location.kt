package com.example.guru_naver_map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.UiSettings
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.LocationButtonView

class Location : AppCompatActivity(), OnMapReadyCallback {
    private val PERMISSION_REQUEST_CODE = 100
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private lateinit var naverMap: NaverMap // 네이버맵 객체를 멤버 변수로 선언
    private lateinit var mLocationSource: FusedLocationSource // 위치 소스를 멤버 변수로 선언


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

    // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = FusedLocationSource(this, PERMISSION_REQUEST_CODE)

        // 네이버지도 초기화
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        naverMap.locationSource = mLocationSource
        // 권한 확인. 결과는 onRequestPermissionsResult 콜백 메서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)

        this.naverMap = naverMap
        //UI설정 현재위치 버튼

        val uiSettings: UiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        val locationButtonView: LocationButtonView = findViewById(R.id.location)
        locationButtonView.map = naverMap
        locationButtonView.visibility = View.GONE


        // 출발지와 목적지 좌표 설정
        val departureLatLng = LatLng( 37.6261, 127.0725)
        val destinationLatLng = LatLng(37.629336, 127.069615)

        // 출발지에서 목적지까지 경로 표시
        showPath(departureLatLng, destinationLatLng)

        // 마커 추가
        val destinationMarker = Marker()
        destinationMarker.position = destinationLatLng
        destinationMarker.map = naverMap
    }

    private fun showPath(departureLatLng: LatLng, destinationLatLng: LatLng) {
        val pathOverlay = PathOverlay()
        val pathPoints = mutableListOf(departureLatLng, destinationLatLng)

        pathOverlay.coords = pathPoints
        pathOverlay.color = Color.RED
        pathOverlay.map = naverMap

        // 출발지와 목적지를 포함한 모든 경로를 표시할 수 있도록 카메라 이동
        val cameraUpdate = CameraUpdate.fitBounds(pathOverlay.bounds)
            .animate(CameraAnimation.Fly, 3000)
            .finishCallback(object : CameraUpdate.FinishCallback {
                override fun onCameraUpdateFinish() {
                    // 경로 안내가 시작되면 여기에 원하는 작업을 추가할 수 있습니다.
                }
            })
        naverMap.moveCamera(cameraUpdate)
    }
//권한
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    // request code와 권한 획득 여부 확인
    if (requestCode == PERMISSION_REQUEST_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }
}}


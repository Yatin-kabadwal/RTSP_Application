package com.bot.rtspstreaming

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.rtsp.RtspMediaSource
import androidx.media3.ui.PlayerView


class MainActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var etRtspUrl: EditText
    private lateinit var btnStartStream: Button
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button

    @androidx.annotation.OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        etRtspUrl = findViewById(R.id.et_rtsp_url)
        btnStartStream = findViewById(R.id.btn_start_stream)
        playerView = findViewById(R.id.player_view)
        btnPlay = findViewById(R.id.btn_play)
        btnPause = findViewById(R.id.btn_pause)
        btnStop = findViewById(R.id.btn_stop)

        // Initialize ExoPlayer
        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        playerView.controllerAutoShow = true // hepls to Show default controls
        player.playWhenReady = true // Auto-start playback when ready

        btnStartStream.setOnClickListener { startStreaming() }
        btnPlay.setOnClickListener { player.play() }
        btnPause.setOnClickListener { player.pause() }
        btnStop.setOnClickListener { stopStreaming() }

        // Adding error listener for better error handling
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Toast.makeText(this@MainActivity, "Playback error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    @androidx.annotation.OptIn(UnstableApi::class)
    private fun startStreaming() {
        val rtspUrl = etRtspUrl.text.toString().trim()
        if (rtspUrl.isEmpty()) {
            Toast.makeText(this, "Please enter an RTSP URL", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            //here it  Create RTSP media source
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(rtspUrl))
                .setMimeType("application/x-rtsp") //this helps to  ensure that it's RTSP type only
                .build()

            val mediaSource = RtspMediaSource.Factory().createMediaSource(mediaItem)

            // Set media source for ExoPlayer
            player.setMediaSource(mediaSource)
            player.prepare()
            player.play()
        } catch (e: Exception) {
            Toast.makeText(this, "Error starting stream: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun stopStreaming() {
        player.stop()
        player.clearMediaItems()
        player.release() // it helps to Free resources properly
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}

# RTSP Streaming App

## 📽️ Overview

This is an Android application that enables users to stream video using the Real-Time Streaming Protocol (RTSP). Built using Kotlin and ExoPlayer, the app provides a smooth streaming experience with play, pause, and stop functionality.

## 🚀 Features

✅ RTSP URL Input - Users can enter an RTSP URL to start streaming.✅ Start, Play, Pause, Stop Controls - Full control over the video stream.✅ Optimized for Performance - Low latency and efficient playback.✅ Error Handling - Displays informative messages if streaming fails.✅ Uses ExoPlayer - Reliable media playback with customizable controls.

## 📌 Usage Guide

Enter RTSP URL in the input field (e.g., rtsp://your-stream-url).

Click Start Streaming to load the video.

Use Play, Pause, and Stop buttons for playback control.

## ⚠️ Challenges Faced & Solutions

1️⃣ RTSP Streaming Issues

Problem: Some URLs did not work or had high latency.Solution: Used ExoPlayer, which has built-in RTSP support for better playback.

2️⃣ Error Handling

Problem: No feedback when streaming fails.Solution: Implemented onPlayerError() to display error messages to the user.

3️⃣ Performance Optimization

Problem: App would crash due to resource leaks.Solution: Properly release ExoPlayer when stopping or exiting the app.

## 📜 License

This project is for educational purposes. Do not distribute without permission.

## 📧 Contact

For any issues or access requests, contact: yatinkabdwal@gmail.com

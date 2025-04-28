package com.thecodingcult.truecommunity.community.presentation.animations
//
//        import androidx.compose.runtime.*
//        import androidx.compose.ui.Modifier
//        import androidx.compose.ui.tooling.preview.Preview
//        import androidx.compose.ui.unit.dp
//        import com.airbnb.lottie.compose.*
//
//        @Composable
//        fun HandshakeLoader(modifier: Modifier = Modifier) {
//            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.handshake))
//            val progress by animateLottieCompositionAsState(
//                composition = composition,
//                iterations = LottieConstants.IterateForever
//            )
//
//            LottieAnimation(
//                composition = composition,
//                progress = progress,
//                modifier = modifier
//            )
//        }
//
//        @Preview
//        @Composable
//        fun PreviewHandshakeLoader() {
//            HandshakeLoader(modifier = Modifier.size(100.dp))
//        }
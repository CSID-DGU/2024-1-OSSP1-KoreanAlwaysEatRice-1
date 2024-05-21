package com.kaer.menuw.util

import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import timber.log.Timber

class KakaoLogInCallback(private val onSuccess: (accessToken: String) -> Unit) {

    fun setKakaoCallback(token: OAuthToken?, error: Throwable?) {
        if (error != null) {
            when {
                error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                    Timber.e("[카카오로그인] -> 접근이 거부됨(동의 취소)")
                }
                error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                    Timber.e("[카카오로그인] -> 유효하지 않은 앱")
                }
                error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                    Timber.e("[카카오로그인] -> 인증 수단이 유효하지 않아 인증할 수 없는 상태")
                }
                error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                    Timber.e("[카카오로그인] -> 요청 파라미터 오류")
                }
                error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                    Timber.e("[카카오로그인] -> 유효하지 않은 scope ID")
                }
                error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                    Timber.e("[카카오로그인] -> 설정이 올바르지 않음(android key hash)")
                }
                error.toString() == AuthErrorCause.ServerError.toString() -> {
                    Timber.e("[카카오로그인] -> 서버 내부 에러")
                }
                error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                    Timber.e("[카카오로그인] -> 앱 요청 권한 없음")
                }
                else -> {
                    Timber.e("[카카오로그인] -> 기타 에러")
                }
            }
        } else if (token != null) {
            Timber.d("[카카오로그인] -> 로그인 성공")
            onSuccess(token.accessToken)
        }
    }
}
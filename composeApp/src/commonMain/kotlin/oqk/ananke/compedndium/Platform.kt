package oqk.ananke.compedndium

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
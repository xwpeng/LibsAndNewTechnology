package com.xwpeng.filmguide.movie

data class Movie(val id : String, val title:String, val overview:String, val posterPath:String){
    override fun toString(): String {
        return "Movie(id = '$id', title = '$title', overview = '$overview', posterPath = '$posterPath')"
    }
    val VOTE_AVERAGE_API = "http://api.themoviedb.org//3/discover/movie" +
            "?certification_country=US&certification=R&sort_by=vote_average.desc&api_key=7e55a88ece9f03408b895a96c1487979"

}
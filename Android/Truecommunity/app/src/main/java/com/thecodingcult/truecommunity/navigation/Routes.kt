package com.thecodingcult.truecommunity.navigation

import android.graphics.drawable.PaintDrawable

sealed class Routes(val route: String) {
    object AppTabLayout : Routes("app_tab_layout")
    object CreateGroup : Routes("create_group")

    object EmergencyLocations : Routes("emergency_locations")
    object FundraisingScreen : Routes("fundraising_screen")



    object GroupsInterface : Routes("groups_interface/{title}") {
        fun createRoute(title: String) = "groups_interface/$title"
    }


    object GroupsChatScreen : Routes("groups_chat_screen/{title}") {
        fun createRoute(title: String) = "groups_chat_screen/$title"
    }


    // dummy classes

    object ClassMateGroupsInterface : Routes("classmate_groups_interface/{title}") {
        fun createRoute(title: String) = "classmate_groups_interface/$title"
    }

    object ClassMateGroupChatScreen : Routes("classmates_groups_chat_screen/{title}") {
        fun createRoute(title: String) = "classmates_groups_chat_screen/$title"
    }

    object BusinessGroupsInterface : Routes("business_groups_interface/{title}") {
        fun createRoute(title: String) = "business_groups_interface/$title"
    }

    object BusinessGroupChatScreen : Routes("business_groups_chat_screen/{title}") {
        fun createRoute(title: String) = "business_groups_chat_screen/$title"
    }

    object ColleaguesGroupsInterface : Routes("colleagues_groups_interface/{title}") {
        fun createRoute(title: String) = "colleagues_groups_interface/$title"
    }

    object ColleaguesGroupChatScreen : Routes("colleagues_groups_chat_screen/{title}") {
        fun createRoute(title: String) = "colleagues_groups_chat_screen/$title"
    }


    object NeighboursGroupsInterface : Routes("neighbours_groups_interface/{title}") {
        fun createRoute(title: String) = "neighbours_groups_interface/$title"
    }

    object NeighboursGroupChatScreen : Routes("neighbours_groups_chat_screen/{title}") {
        fun createRoute(title: String) = "neighbours_groups_chat_screen/$title"
    }


    object EmergencyGroupsInterface : Routes("emergency_groups_interface/{title}") {
        fun createRoute(title: String) = "emergency_groups_interface/$title"
    }

    object EmergencyGroupChatScreen : Routes("emergency_groups_chat_screen/{title}") {
        fun createRoute(title: String) = "emergency_groups_chat_screen/$title"
    }

}


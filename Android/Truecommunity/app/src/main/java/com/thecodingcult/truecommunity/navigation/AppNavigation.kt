package com.thecodingcult.truecommunity.navigation

import EmergencyLocations
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thecodingcult.truecommunity.community.presentation.CreateGroup
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Business.BusinessGroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Business.BusinessGroupInterface
import com.thecodingcult.truecommunity.community.presentation.IMPComps.ClassMateGroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.ClassMateGroupInterface
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Colleagues.ColleaguesGroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Colleagues.ColleaguesGroupInterface
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Emergency.EmergencyGroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Emergency.EmergencyGroupInterface
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Funds.FundraisingScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Neighbours.NeighboursGroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Neighbours.NeighboursGroupInterface
import com.thecodingcult.truecommunity.community.presentation.Family.GroupChatScreen
import com.thecodingcult.truecommunity.community.presentation.Family.GroupsInterfaceScreen
import com.thecodingcult.truecommunity.core.presentation.components.AppTabLayout

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.AppTabLayout.route) {
        composable(Routes.AppTabLayout.route) {
            AppTabLayout(navController)
        }
        composable(
            route = Routes.GroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            GroupsInterfaceScreen(navController, title)
        }


        composable(Routes.CreateGroup.route) {
            CreateGroup(navController)
        }


        composable(Routes.EmergencyLocations.route) {
            EmergencyLocations(navController)
        }



        composable(
            route = Routes.GroupsChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            GroupChatScreen(navController, title)
        }


        // dummy data navigations

        composable(Routes.FundraisingScreen.route) {
            FundraisingScreen(navController)
        }




        composable(
            route = Routes.ClassMateGroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            ClassMateGroupInterface(navController, title)
        }


        composable(
            route = Routes.ClassMateGroupChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            ClassMateGroupChatScreen(navController, title)
        }

        composable(
            route = Routes.BusinessGroupChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            BusinessGroupChatScreen(navController, title)
        }

        composable(
            route = Routes.BusinessGroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            BusinessGroupInterface(navController, title)
        }


        composable(
            route = Routes.ColleaguesGroupChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            ColleaguesGroupChatScreen(navController, title)
        }

        composable(
            route = Routes.ColleaguesGroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            ColleaguesGroupInterface(navController, title)
        }


        composable(
            route = Routes.NeighboursGroupChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            NeighboursGroupChatScreen(navController, title)
        }

        composable(
            route = Routes.NeighboursGroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            NeighboursGroupInterface(navController, title)
        }

        composable(
            route = Routes.EmergencyGroupChatScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            EmergencyGroupChatScreen(navController, title)
        }

        composable(
            route = Routes.EmergencyGroupsInterface.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            EmergencyGroupInterface(navController, title)
        }


    }
}
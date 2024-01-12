package com.promofusion.modules.main.fragments.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.promofusion.R
import com.promofusion.common.components.HeaderTitle
import com.promofusion.common.components.SearchBar
import com.promofusion.common.theme.PromoFusionTheme
import com.promofusion.common.utils.SessionManager
import com.promofusion.modules.main.fragments.home.viewmodels.HomeViewModel
import com.promofusion.modules.search.navigations.models.SearchNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController? = null) {
    val homeViewModel: HomeViewModel = viewModel<HomeViewModel>()

    // Fetch user data from session manager
    val userData = SessionManager(LocalContext.current).fetchCurrentUser()
    // Show user email if logged in, else show "Welcome!"
    val email = userData?.email ?: "Welcome!"


    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp), modifier = Modifier.verticalScroll(
            rememberScrollState()
        )
    ) {

        HeaderTitle(
            title = "Welcome back!", description = email, action = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = (painterResource(id = R.drawable.ic_notification_line)),
                        contentDescription = "Notification Bell",
                    )
                }
            }, modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 0.dp)
        )

        SearchBar(enabled = true, modifier = Modifier.padding(24.dp, 0.dp), onActiveChange = {
            if (it) navController?.navigate(SearchNavigation.Default.route)
        }) {}

        HomeFeaturedSection()

        HomeCategoriesSection(homeViewModel)

        HomeExploreSection()

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PromoFusionTheme {
        HomeScreen()
    }
}


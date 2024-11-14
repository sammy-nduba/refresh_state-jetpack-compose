package com.example.block_app.ui.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.block_app.employee.data.apiService.model.EmployeeX
import com.example.block_app.ui.viewModel.EmployeeViewModel
import com.example.block_app.ui.viewModel.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomePage(navController: NavController) {
    val repository = com.example.block_app.employee.data.repository.EmployeeRepository(
        com.example.block_app.employee.data.apiService.ApiConstants.api
    )
    val employeeViewModel = viewModel(modelClass = EmployeeViewModel::class.java,
        factory = ViewModelFactory(repository)
    )
    val employee = employeeViewModel.employee.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    val refreshScope = rememberCoroutineScope()
    val refreshState = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() =
        refreshScope.launch {
            refreshing = true
            delay(1500)
            refreshing = false
        }
    val state = rememberPullRefreshState(refreshing, ::refresh)


    Scaffold(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(0.dp), topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ), title = {
                Text(
                    text = "Block Employees", color = Color.Black, maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
            }, actions = {
                IconButton(onClick = { /*TODO: Handle Search Action*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search, tint = Color.Black,
                        contentDescription = "Search"
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )

    }) { innerPadding ->
        Box(
            Modifier.padding(innerPadding).fillMaxWidth().pullRefresh(state)
                .wrapContentHeight(),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp),

                state = rememberLazyListState()
            ) {
                employeeViewModel.getEmployee()

                if (employee.value.employees.isEmpty()) {

                    refresh()
//                    item {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .wrapContentSize(align = Alignment.Center)
//                        )
//                    }

                } else {
                    if (!refreshing) {
                        items(employee.value.employees.size) { employeeItem ->
                            Log.d("employee", employee.value.employees.toString())
                            ScrollColumns(employee = employee.value.employees[employeeItem])
                        }
                    }
                }
            }

            PullRefreshIndicator(refreshing, state, modifier = Modifier.align(Alignment.TopCenter))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScrollColumns(employee: EmployeeX ) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding = if (expanded) 48.dp else 0.dp
    

    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Gray)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = extraPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = employee.photo_url_small,
                contentDescription = "employee-photo",
                modifier = Modifier
                    .size(100.dp)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape)
                    .padding(2.dp),
            )
            Column(modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
                .padding(start = 10.dp, top = 10.dp)) {

                Text(
                    text = employee.full_name,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = employee.team,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = employee.employee_type,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(15.dp))
                if (expanded) {
                    Text(
                        text = employee.biography,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = employee.uuid,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = employee.email_address,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = employee.phone_number,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }

            }
            ElevatedButton(
                onClick = {expanded = !expanded},
                Modifier
                    .background(color = Color.Gray)
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Text(if (expanded) "Show less" else "Show more")
    }
        }

    }

}


package com.example.aborea.pages.goal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.ExperimentalLayoutApi

// [추가된 Import]: 구매한 나무 목록을 가져오기 위해 필요
import androidx.compose.ui.platform.LocalContext
import com.example.aborea.pages.store.* // ==========================================================
// 1. 전역 상태 관리 및 목표 데이터 모델 정의 (Goal2.kt와 공유)
// ==========================================================

data class SelectionItem(
    val id: Int,
    val label: String,
    val emoji: String? = null,
    val color: Color = Color.LightGray
)

// 2. 재사용 가능한 카드 버튼 컴포넌트
@Composable
fun SelectButton(
    item: SelectionItem,
    isSelected: Boolean,
    onItemClick: (Int) -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFE8F5E9) else Color(0xFFF8F8F8)
    val borderColor = Color(0xFF8BC34A)

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clickable { onItemClick(item.id) }
            .then(
                if (isSelected) Modifier.border(1.dp, borderColor, RoundedCornerShape(8.dp))
                else Modifier
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            item.emoji?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
            Text(
                text = item.label,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}


// 3. 메인 화면 컴포넌트 (NewGoalScreen)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewGoalScreen(navController: NavController) {

    // Context 가져오기
    val context = LocalContext.current

    // 목표 이름 상태 변수
    var goalName by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf(-1) }
    var selectedTreeId by remember { mutableStateOf(-1) }

    // 데이터 정의 (GoalItem 생성에 사용)
    val categories = remember {
        listOf(
            SelectionItem(1, "공부", "📚"),
            SelectionItem(2, "운동", "💪"),
            SelectionItem(3, "업무", "💼"),
            SelectionItem(4, "취미", "💜")
        )
    }

    // ******************************************************
    // [수정된 부분] 구매한 StoreItem 목록을 불러와 SelectionItem 목록으로 변환
    // ******************************************************

    // 1. 구매한 StoreItem 목록을 불러옵니다. (SharedPreferences 접근)
    val purchasedStoreItems: List<StoreItem> = remember(context) {
        getMyTreeList(context)
    }

    // 2. StoreItem 목록을 Goal1에서 사용하는 SelectionItem 목록으로 변환합니다.
    val treeTypes: List<SelectionItem> = purchasedStoreItems.map { storeItem ->

        // **요청하신 대로 나무 이름에 따른 이모지 매핑**
        val emoji = when (storeItem.name) {
            "흐릿나무" -> "🌫️"
            "대나무" -> "🎋"
            "단풍나무" -> "🍁"
            "참나무" -> "🌳"
            "별나무" -> "⭐"
            "벚꽃나무" -> "🌸"
            "달나무" -> "🌙"
            "반디나무" -> "✨"
            "사과나무" -> "🍎"
            "소나무" -> "🌲"
            "버드나무" -> "🍃"
            "자작나무" -> "🍂"
            else -> "❓"
        }

        SelectionItem(
            id = storeItem.id,
            label = storeItem.name,
            emoji = emoji // 매핑된 이모지 사용
        )
    }

    // ******************************************************
    // ******************************************************

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. 헤더 (유지)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(24.dp))
            Text("새로운 목표", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Text(
                text = "X",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { navController.popBackStack() }
                    .padding(8.dp)
            )
        }

        // 2. 목표 이름 입력 (유지)
        Text(
            "목표 이름",
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
        OutlinedTextField(
            value = goalName,
            onValueChange = { goalName = it },
            placeholder = { Text("예: 객프 공부", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color.White
            )
        )

        // 3. 카테고리 선택 (2열) (유지)
        Text(
            "카테고리 선택",
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Start,
            maxItemsInEachRow = 2
        ) {
            categories.forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                ) {
                    SelectButton(
                        item = item,
                        isSelected = item.id == selectedCategoryId,
                        onItemClick = { newId -> selectedCategoryId = newId }
                    )
                }
            }
        }

        // 4. 나무 종류 선택 (2열)
        Text(
            "나무 종류 선택",
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Start,
            maxItemsInEachRow = 2
        ) {
            // 이제 treeTypes는 구매한 나무 목록입니다.
            treeTypes.forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                ) {
                    SelectButton(
                        item = item,
                        isSelected = item.id == selectedTreeId,
                        onItemClick = { newId -> selectedTreeId = newId }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 5. 목표 생성 버튼 (유지)
        Button(
            onClick = {
                if (goalName.isBlank() || selectedCategoryId == -1 || selectedTreeId == -1) {
                    println("오류: 모든 항목을 선택/입력해야 합니다.")
                    return@Button
                }

                // 목표 데이터 생성 및 GoalState에 추가
                val newGoal = GoalItem(
                    id = GoalState.goalList.size + 1,
                    categoryEmoji = categories.find { it.id == selectedCategoryId }?.emoji ?: "❓",
                    categoryLabel = categories.find { it.id == selectedCategoryId }?.label ?: "기타",
                    name = goalName,
                    // 선택된 나무 정보는 이제 구매한 목록(treeTypes)에서 정확한 이모지를 가져옵니다.
                    treeEmoji = treeTypes.find { it.id == selectedTreeId }?.emoji ?: "❓",
                    treeName = treeTypes.find { it.id == selectedTreeId }?.label ?: "기타나무",
                    isActive = GoalState.goalList.isEmpty()
                )

                GoalState.goalList.add(newGoal)

                // Goal2 (목표 목록/관리 페이지)로 이동
                navController.navigate("goal2") {
                    popUpTo("goal2") { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("목표 생성", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 4. 메인 Entry Point (Goal1 함수)
@Composable
fun Goal1(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        NewGoalScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun Goal1Preview() {
    Goal1(rememberNavController())
}
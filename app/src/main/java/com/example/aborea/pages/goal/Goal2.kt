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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aborea.common.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateListOf

// ==========================================================
// 1. 전역 상태 관리 및 목표 데이터 모델 정의 (Goal1.kt와 공유)
// ==========================================================
data class GoalItem(
    val id: Int,
    val categoryEmoji: String,
    val categoryLabel: String,
    val name: String,
    val treeEmoji: String,
    val treeName: String,
    val isActive: Boolean = false
)

object GoalState {
    val goalList = mutableStateListOf<GoalItem>()
}
// ==========================================================

// 현재 활성 목표 카드
@Composable
fun ActiveGoalCard(goal: GoalItem, onDeactivate: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)), // 연두색 배경
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, Color(0xFF8BC34A), RoundedCornerShape(8.dp)) // 초록색 테두리
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // "현재 활성 목표" 타이틀
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("ⓒ", color = Color(0xFF8BC34A), fontSize = 16.sp)
                Spacer(Modifier.width(4.dp))
                Text("현재 활성 목표", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(Modifier.height(8.dp))

            // 목표 상세 정보 및 해제 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(goal.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(goal.treeEmoji + " " + goal.treeName, fontSize = 14.sp, color = Color.Gray)
                }

                TextButton(onClick = onDeactivate) {
                    Text("해제", color = Color.Gray, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}



// GoalCard 수정: 클릭 핸들러 및 활성 상태 표시 추가
@Composable
fun GoalCard(
    goal: GoalItem,
    onGoalClick: (GoalItem) -> Unit,
    onDelete: (GoalItem) -> Unit
) {
    val containerColor = if (goal.isActive) Color(0xFFF8F8F8) else Color.White
    val borderColor = if (goal.isActive) Color(0xFF8BC34A) else Color.LightGray

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onGoalClick(goal) } // 활성화/비활성화 클릭 이벤트
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(goal.categoryEmoji, fontSize = 20.sp)
            Spacer(Modifier.width(8.dp))
            Column {
                Text(goal.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(goal.treeEmoji + " " + goal.treeName, fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(Modifier.weight(1f))

            if (goal.isActive) {
                Text("✓", color = Color(0xFF8BC34A), fontSize = 20.sp) // 활성화 시 체크 표시
                Spacer(Modifier.width(10.dp))
            }

            // 삭제 버튼
            Text("🗑️", modifier = Modifier.clickable { onDelete(goal) })
        }
    }
}



// ==========================================================
// 2. GoalManagementScreen
// ==========================================================

@Composable
fun GoalManagementScreen(navController: NavController, goals: List<GoalItem>) {

    val hasGoals = goals.isNotEmpty()

    // 활성 목표 관리 로직
    val setActive: (GoalItem) -> Unit = { selectedGoal ->
        // 1. 모든 목표 비활성화
        GoalState.goalList.replaceAll { it.copy(isActive = false) }
        // 2. 선택된 목표 활성화
        val index = GoalState.goalList.indexOfFirst { it.id == selectedGoal.id }
        if (index != -1) {
            // 새롭게 복사된 GoalItem을 대입하여 상태 업데이트 강제
            GoalState.goalList[index] = selectedGoal.copy(isActive = true)
        }
    }

    val clearActive: () -> Unit = {
        // 모든 목표 비활성화 (해제)
        GoalState.goalList.replaceAll { it.copy(isActive = false) }
    }

    val activeGoal = goals.find { it.isActive }
    // 활성 목표 관리 로직 끝


    // 외곽 컨테이너 Column: NavBar를 제외한 공간을 채웁니다.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 1. 상단 여백 (50.dp 유지)
        Spacer(modifier = Modifier.height(50.dp))

        // 2. 흰색 박스 (Card) 구조 유지
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {

            // 박스 내부 콘텐츠 Column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 60.dp), // Card 내부 패딩 유지
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                // 2. 화면 타이틀 (모든 상태에서 동일)
                Text(
                    "목표 관리",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    "카테고리별로 나무를 키워보세요",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // 3. 새 목표 만들기 버튼 (모든 상태에서 동일)
                Button(
                    onClick = { navController.navigate("goal1") },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Light)
                        Spacer(Modifier.width(8.dp))
                        Text("새 목표 만들기", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // 목표 유무에 따른 동적 콘텐츠 영역
                if (hasGoals) {

                    // 활성 목표 카드 표시
                    activeGoal?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        ActiveGoalCard(goal = it, onDeactivate = clearActive)
                    }
                    // 활성 목표 카드 표시 끝

                    Spacer(modifier = Modifier.height(16.dp)) // 활성 목표 카드/버튼과 목록 사이 간격

                    // 목표 목록 LazyColumn
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 20.dp)
                    ) {
                        items(goals.reversed()) { goal ->
                            GoalCard(
                                goal = goal,
                                onGoalClick = setActive, // 활성 목표 설정 핸들러 전달
                                onDelete = { GoalState.goalList.remove(it) } // 삭제 핸들러 전달
                            )
                        }
                    }

                } else {
                    // 목표 없음 상태
                    Spacer(modifier = Modifier.height(100.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("🙂", fontSize = 54.sp)
                        Spacer(Modifier.height(12.dp))
                        Text("아직 목표가 없습니다", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Text("새 목표를 만들어보세요", fontSize = 16.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }

                // 5. 사용 팁 섹션 (목표가 없을 때만 표시)
                if (!hasGoals) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FFF0)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF8BC34A), RoundedCornerShape(10.dp))
                    ) {
                        Column(modifier = Modifier.padding(27.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("💡", fontSize = 20.sp, modifier = Modifier.size(20.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("사용 팁", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(text = "• 목표를 선택하고 타이머를 시작하면 해당 나무가 자랍니다", fontSize = 13.5.sp)
                            Spacer(Modifier.height(8.dp))
                            Text(text = "• 목표를 선택하지 않으면 기본 나무가 자랍니다", fontSize = 13.5.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}


// ==========================================================
// 3. 메인 Entry Point: Goal2
// ==========================================================
@Composable
fun Goal2(navController: NavController) {
    SetBackground()

    val goals = GoalState.goalList

    Box(modifier = Modifier.fillMaxSize()) {

        GoalManagementScreen(navController, goals)

        // 하단에 NavBar 배치
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NavBar(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Goal2Preview() {
    Goal2(rememberNavController())
}
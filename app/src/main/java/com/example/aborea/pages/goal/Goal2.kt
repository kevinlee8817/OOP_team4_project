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

// 임시 목표 카드 컴포넌트
@Composable
fun GoalCard(goal: GoalItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
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
            Text("🗑️", modifier = Modifier.clickable { GoalState.goalList.remove(goal) })
        }
    }
}


// ==========================================================
// 2. MainContent 내부 UI 컴포넌트 (하나로 통합하여 레이아웃 통일)
// ==========================================================

@Composable
fun GoalManagementScreen(navController: NavController, goals: List<GoalItem>) {

    val hasGoals = goals.isNotEmpty()

    // 외곽 컨테이너 Column: NavBar를 제외한 공간을 채웁니다.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // ⭐ 1. 상단 여백 (50.dp 유지)
        Spacer(modifier = Modifier.height(50.dp))

        // ⭐ 2. 흰색 박스 (Card) 구조 유지
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {

            // 박스 내부 콘텐츠 Column (스크롤이 필요 없는 상/하단 고정 콘텐츠)
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

                // ⭐⭐ 목표 유무에 따른 동적 콘텐츠 영역 ⭐⭐
                if (hasGoals) {
                    // 목표 있음 상태: 목표 목록은 스크롤 가능해야 하므로 LazyColumn을 사용
                    Spacer(modifier = Modifier.height(16.dp)) // 버튼과 목록 사이 간격

                    // Column의 남은 공간을 채우기 위해 LazyColumn을 weight(1f)로 설정하고 스크롤 가능하게 함
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 20.dp) // 사용 팁과의 간격 확보
                    ) {
                        items(goals.reversed()) { goal ->
                            GoalCard(goal = goal)
                        }
                    }

                } else {
                    // 목표 없음 상태: 메시지 영역은 스크롤 불필요, 고정 높이 Spacer로 레이아웃 고정

                    // 4. 목표 없음 메시지 영역 (목표 없음 상태에서만 표시)
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

                    // 이 영역에서 weight(1f)를 주지 않으므로, 사용 팁은 항상 고정된 위치에 배치됨.
                }

                // 5. 사용 팁 섹션 (모든 상태에서 하단에 고정)
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

        // 목표 유무와 관계없이 하나의 통일된 레이아웃 구조 호출
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
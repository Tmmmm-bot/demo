<template>
	<view class="container">
		<!-- 页面标题 -->
	
		
		<!-- 调车作业统计 -->
		<view class="stats-section">
			<view class="stats-grid">
				<!-- 全部按钮 -->
				<view class="stat-item all-item" :class="{'active': currentFilter === -1}" @tap="showAll">
					<view class="stat-number">{{totalCount}}</view>
					<view class="stat-label">全部</view>
				</view>
				<view class="stat-item" :class="{'active': currentFilter === index}" v-for="(stat, index) in operationStats" :key="index" @tap="filterByStatus(index)">
					<view class="stat-number">{{stat.count}}</view>
					<view class="stat-label">{{stat.text}}</view>
				</view>
			</view>
		</view>
		<view class="feedback-section">
					<button class="feedback-btn" @tap="toFeedback">
						<text class="feedback-icon">💬</text>
						<text class="feedback-text">意见反馈</text>
					</button>
					<button  style="margin-top: 20upx; background-color: cadetblue; color: #fff;" @tap="toOut">
						<text class="feedback-text">out 退出登录</text>
					</button>
		</view>
		
		<!-- 调车作业列表 -->
		<view class="operation-section">
			<view class="section-header">
				<view class="section-title">作业列表</view>
				<view class="filter-status" v-if="currentFilter !== -1">
					<text class="filter-text">筛选：{{operationStats[currentFilter].text}}</text>
					<text class="clear-filter" @tap="clearFilter">清除</text>
				</view>
			</view>
			<view class="operation-list">
				<view class="operation-item" v-for="(operation, index) in operationList" :key="operation.id" @tap="toOperationDetail(operation)">
					<view class="operation-header">
						<view class="operation-type">{{operation.operationType}}</view>
						<view class="priority-badge"  :class="[`getPriorityClass{operation.priority}`]" >
							{{getPriorityText(operation.priority)}}
						</view>
					</view>
					
					<view class="operation-content">
						<text class="content-text">{{operation.operationContent}}</text>
					</view>
					
					<view class="route-info">
						<text class="start-station">{{operation.startStationName}}</text>
						<text class="arrow">→</text>
						<text class="end-station">{{operation.endStationName}}</text>
					</view>
					
					<view class="time-info">
						<text class="time-label">计划时间：</text>
						<text class="time-text">{{formatDateTime(operation.plannedStartTime)}} - {{formatDateTime(operation.plannedEndTime)}}</text>
					</view>
					
					<view class="operation-details">
						<text class="operator">作业员：{{operation.operator}}</text>
						<text class="car-count">车辆：{{operation.carCount}}辆</text>
					</view>
					
					<view class="operation-footer">
						<view class="status"  :class="[`getStatusClass{operation.status}`]" >{{getStatusText(operation.status)}}</view>
						<view class="safety-check" :class="operation.safetyCheck ? 'checked' : 'unchecked'">
							{{operation.safetyCheck ? '已检查' : '未检查'}}
						</view>
					</view>
				</view>
			</view>
		</view>
		
		<!-- 意见反馈按钮 -->
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 调车作业统计
				operationStats:[
					{text:'未开始',count:0},
					{text:'进行中',count:0},
					{text:'已完成',count:0},
					{text:'已取消',count:0}
				],
				// 调车作业列表
				operationList: [],
				// 原始数据（用于筛选）
				originalOperationList: [],
				// 当前筛选状态
				currentFilter: -1, // -1表示显示全部，0-3表示筛选对应状态
				// 总数统计
				totalCount: 0
			}
		},
		
		//下拉刷新
		onPullDownRefresh() {
			this.loadOperationData();
		    setTimeout(function () {
		        uni.stopPullDownRefresh();
		    }, 1000);
		},
		
		onLoad() {
			// 加载调车作业数据
			this.loadOperationData();
		},
		
		onShow(){
			// 页面显示时刷新数据
			this.loadOperationData();
		},
		
		methods: {
			// 加载调车作业数据
			loadOperationData() {
				var that = this;
				uni.request({
					url: uni.getStorageSync("url") + '/shunting-operation/findAll',
					method: "POST",
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: function(res) {
						if(res.data.code == 0) {
							that.operationList = res.data.data;
							that.originalOperationList = res.data.data;
							that.calculateStats();
							console.log("调车作业数据: " + JSON.stringify(that.operationList));
						} else {
							uni.showToast({
								title: res.data.msg || '获取数据失败'
							})
						}
					},
					fail: function(res) {
						console.log(JSON.stringify(res));
						uni.showToast({
							title: '网络请求失败'
						})
					}
				});
			},
			
			// 计算统计数据
			calculateStats() {
				let stats = [0, 0, 0, 0]; // 对应状态 0,1,2,3
				this.originalOperationList.forEach(operation => {
					if(operation.status >= 0 && operation.status <= 3) {
						stats[operation.status]++;
					}
				});
				
				for(let i = 0; i < 4; i++) {
					this.operationStats[i].count = stats[i];
				}
				this.totalCount = stats.reduce((a, b) => a + b, 0);
			},
			
			// 按状态筛选
			filterByStatus(statusIndex) {
				this.currentFilter = statusIndex;
				this.filterOperations();
			},
			
			// 过滤操作
			filterOperations() {
				if (this.currentFilter === -1) {
					this.operationList = this.originalOperationList;
				} else {
					this.operationList = this.originalOperationList.filter(operation => operation.status === this.currentFilter);
				}
			},
			
			// 跳转到作业详情
			toOperationDetail(operation) {
				var mdata = JSON.stringify(operation);
				mdata = encodeURIComponent(mdata);
				uni.navigateTo({
					url: '../../goods/goods?data=' + mdata
				});
			},
			
			// 跳转到意见反馈页面
			toFeedback() {
				uni.navigateTo({
					url: '/pages/user/feedback/feedback'
				});
			},
			
			// 获取优先级文本
			getPriorityText(priority) {
				const priorityMap = {
					0: '普通',
					1: '重要', 
					2: '紧急'
				};
				return priorityMap[priority] || '普通';
			},
			
			// 获取优先级样式类
			getPriorityClass(priority) {
				const classMap = {
					0: 'normal',
					1: 'important',
					2: 'urgent'
				};
				return classMap[priority] || 'normal';
			},
			
			// 获取状态文本
			getStatusText(status) {
				const statusMap = {
					0: '未开始',
					1: '进行中',
					2: '已完成',
					3: '已取消'
				};
				return statusMap[status] || '未知';
			},
			
			// 获取状态样式类
			getStatusClass(status) {
				const classMap = {
					0: 'pending',
					1: 'executing',
					2: 'completed',
					3: 'cancelled'
				};
				return classMap[status] || 'pending';
			},
			
			// 格式化日期时间
			formatDateTime(timeStr) {
				if (!timeStr) return '--';
				const date = new Date(timeStr);
				const month = (date.getMonth() + 1).toString().padStart(2, '0');
				const day = date.getDate().toString().padStart(2, '0');
				const hours = date.getHours().toString().padStart(2, '0');
				const minutes = date.getMinutes().toString().padStart(2, '0');
				return `${month}-${day} ${hours}:${minutes}`;
			},
			
			// 清除筛选
			clearFilter() {
				this.currentFilter = -1;
				this.filterOperations();
			},
			
			// 显示全部
			showAll() {
				this.currentFilter = -1;
				this.filterOperations();
			},
			toOut(){
				uni.reLaunch({
					url:"/pages/login/login"
				})
			}
		}
	} 
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5;
		height: 100%;
	}
	
	.container {
		min-height: 100vh;
		padding-bottom: 40upx;
	}
	
	.page-header {
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		padding: 60upx 40upx 40upx 40upx;
		text-align: center;
		
		.page-title {
			font-size: 36upx;
			font-weight: bold;
			color: #ffffff;
		}
	}
	
	.stats-section {
		margin: 30upx 30upx;
		background-color: #ffffff;
		border-radius: 20upx;
		padding: 30upx;
		box-shadow: 0 4upx 20upx rgba(0,0,0,0.1);
		
		.stats-grid {
			display: flex;
			justify-content: space-between;
			flex-wrap: wrap;
			gap: 15upx;
			
			.stat-item {
				text-align: center;
				flex: 0 0 14%;
				padding: 10upx;
				border-radius: 12upx;
				transition: all 0.3s ease;
				
				&:active {
					transform: scale(0.95);
				}
				
				&.active {
					background-color: #e3f2fd;
					
					.stat-number {
						color: #1976d2;
					}
					
					.stat-label {
						color: #1976d2;
					}
				}
				
				&.all-item.active {
					background-color: #e8f5e8;
					
					.stat-number {
						color: #2e7d32;
					}
					
					.stat-label {
						color: #2e7d32;
					}
				}
				
				.stat-number {
					font-size: 48upx;
					font-weight: bold;
					color: #2980b9;
					margin-bottom: 8upx;
					transition: color 0.3s ease;
				}
				
				.stat-label {
					font-size: 26upx;
					color: #7f8c8d;
					transition: color 0.3s ease;
				}
			}
		}
	}
	
	.operation-section {
		margin: 0 30upx;
		
		.section-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 20upx;
			
			.section-title {
				font-size: 32upx;
				font-weight: bold;
				color: #2c3e50;
			}
			
			.filter-status {
				display: flex;
				align-items: center;
				
				.filter-text {
					font-size: 26upx;
					color: #7f8c8d;
					margin-right: 12upx;
				}
				
				.clear-filter {
					font-size: 26upx;
					color: #7f8c8d;
					text-decoration: underline;
				}
			}
		}
		
		.operation-list {
			.operation-item {
				background-color: #ffffff;
				border-radius: 20upx;
				padding: 30upx;
				margin-bottom: 20upx;
				box-shadow: 0 4upx 20upx rgba(0,0,0,0.08);
				
				&:active {
					transform: scale(0.98);
					transition: all 0.2s ease;
				}
				
				.operation-header {
					display: flex;
					justify-content: space-between;
					align-items: center;
					margin-bottom: 20upx;
					
					.operation-type {
						font-size: 32upx;
						font-weight: bold;
						color: #2c3e50;
					}
					
					.priority-badge {
						padding: 8upx 16upx;
						border-radius: 20upx;
						font-size: 24upx;
						color: #ffffff;
						
						&.normal {
							background-color: #95a5a6;
						}
						
						&.important {
							background-color: #f39c12;
						}
						
						&.urgent {
							background-color: #e74c3c;
						}
					}
				}
				
				.operation-content {
					margin-bottom: 20upx;
					
					.content-text {
						font-size: 28upx;
						color: #34495e;
						line-height: 1.5;
					}
				}
				
				.route-info {
					display: flex;
					align-items: center;
					margin-bottom: 16upx;
					
					.start-station, .end-station {
						font-size: 28upx;
						color: #27ae60;
						font-weight: 500;
					}
					
					.arrow {
						margin: 0 12upx;
						font-size: 26upx;
						color: #7f8c8d;
					}
				}
				
				.time-info {
					margin-bottom: 16upx;
					
					.time-label {
						font-size: 26upx;
						color: #7f8c8d;
					}
					
					.time-text {
						font-size: 26upx;
						color: #34495e;
					}
				}
				
				.operation-details {
					display: flex;
					justify-content: space-between;
					margin-bottom: 20upx;
					
					.operator, .car-count {
						font-size: 26upx;
						color: #7f8c8d;
					}
				}
				
				.operation-footer {
					display: flex;
					justify-content: space-between;
					align-items: center;
					padding-top: 20upx;
					border-top: 2upx solid #ecf0f1;
					
					.status {
						padding: 8upx 20upx;
						border-radius: 20upx;
						font-size: 24upx;
						font-weight: 500;
						
						&.pending {
							background-color: #f8f9fa;
							color: #6c757d;
						}
						
						&.executing {
							background-color: #e3f2fd;
							color: #1976d2;
						}
						
						&.completed {
							background-color: #e8f5e8;
							color: #2e7d32;
						}
						
						&.cancelled {
							background-color: #ffebee;
							color: #c62828;
						}
					}
					
					.safety-check {
						font-size: 24upx;
						padding: 8upx 16upx;
						border-radius: 16upx;
						
						&.checked {
							background-color: #e8f5e8;
							color: #2e7d32;
						}
						
						&.unchecked {
							background-color: #ffebee;
							color: #c62828;
						}
					}
				}
			}
		}
	}
	
	.feedback-section {
		margin: 40upx 30upx 20upx 30upx;
		
		.feedback-btn {
			background: linear-gradient(135deg, #ff6b6b, #ee5a6f);
			border-radius: 25upx;
			height: 100upx;
			display: flex;
			align-items: center;
			justify-content: center;
			box-shadow: 0 8upx 25upx rgba(255, 107, 107, 0.3);
			border: none;
			
			&:active {
				transform: scale(0.95);
				transition: all 0.2s ease;
			}
			
			.feedback-icon {
				font-size: 36upx;
				margin-right: 12upx;
			}
			
			.feedback-text {
				font-size: 32upx;
				font-weight: bold;
				color: #ffffff;
			}
		}
	}
</style>

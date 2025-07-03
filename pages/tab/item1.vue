<template>
	<view class="container">
		<!-- 乘务组列表 -->
		<view class="crew-list" style="margin-bottom: 100upx;">
			<view class="crew-item" v-for="(crew, index) in crewList" :key="crew.id" @tap="viewCrewDetail(crew)">
				<view class="crew-header">
					<view class="crew-name">{{crew.crewName}}</view>
					<view class="status-badge" :class="[`getStatusClass{crew.status}`]" >
						{{getStatusText(crew.status)}}
					</view>
				</view>
				
				<view class="crew-info">
					<text class="driver-info">司机：{{crew.driverName}} | {{crew.driverPhone}}</text>
				</view>
				
				<view class="route-info">
					<text class="route">{{crew.startStationName}} → {{crew.endStationName}}</text>
					<text class="qualification">{{crew.qualificationLevel}}</text>
				</view>
				
				<view class="detail-info">
					<text class="experience">工作年限：{{crew.workExperience}}年</text>
					<text class="update-time">{{formatDateTime(crew.updateTime)}}</text>
				</view>
				
				<view class="safety-info" v-if="crew.safetyRecord">
					<text class="safety-text">安全记录：{{crew.safetyRecord}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			// 乘务组列表
			crewList: []
		};
	},
	
	onLoad(option) {
		console.log("接收参数: " + JSON.stringify(option));
		this.loadCrewList();
	},

	methods: {
		// 加载乘务组数据
		loadCrewList() {
			var that = this;
			uni.request({
				url: uni.getStorageSync("url") + '/crew-management/findAll',
				method: "POST",
				header: {
					'content-type': 'application/x-www-form-urlencoded'
				},
				success: function(res) {
					if(res.data.code == 0) {
						that.crewList = res.data.data;
						console.log("乘务组数据: " + JSON.stringify(that.crewList));
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

		// 查看乘务组详情
		viewCrewDetail(crew) {
			uni.showToast({
				title: '查看' + crew.crewName + '详情',
				icon: 'none'
			});
		},

		// 获取状态文本
		getStatusText(status) {
			const statusMap = {
				0: '待命',
				1: '在岗',
				2: '休息'
			};
			return statusMap[status] || '未知';
		},

		// 获取状态样式类
		getStatusClass(status) {
			const classMap = {
				0: 'standby',
				1: 'onduty',
				2: 'rest'
			};
			return classMap[status] || 'standby';
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
		}
	}
};
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5;
		height: 100%;
	}
	
	.container {
		
		padding: 20upx;
	}
	
	.crew-list {
		
		.crew-item {
			background-color: #ffffff;
			border-radius: 16upx;
			padding: 30upx;
			margin-bottom: 20upx;
			box-shadow: 0 4upx 20upx rgba(0,0,0,0.08);
			
			&:active {
				transform: scale(0.98);
				transition: all 0.2s ease;
			}
			
			.crew-header {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 20upx;
				
				.crew-name {
					font-size: 32upx;
					font-weight: bold;
					color: #2c3e50;
				}
				
				.status-badge {
					padding: 8upx 20upx;
					border-radius: 20upx;
					font-size: 24upx;
					color: #ffffff;
					
					&.standby {
						background-color: #f39c12;
					}
					
					&.onduty {
						background-color: #27ae60;
					}
					
					&.rest {
						background-color: #95a5a6;
					}
				}
			}
			
			.crew-info {
				margin-bottom: 16upx;
				
				.driver-info {
					font-size: 28upx;
					color: #34495e;
				}
			}
			
			.route-info {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 16upx;
				
				.route {
					font-size: 26upx;
					color: #3498db;
					font-weight: 500;
				}
				
				.qualification {
					font-size: 24upx;
					color: #e74c3c;
					background-color: rgba(231, 76, 60, 0.1);
					padding: 4upx 12upx;
					border-radius: 12upx;
				}
			}
			
			.detail-info {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 16upx;
				
				.experience {
					font-size: 24upx;
					color: #7f8c8d;
				}
				
				.update-time {
					font-size: 22upx;
					color: #95a5a6;
				}
			}
			
			.safety-info {
				border-top: 2upx solid #ecf0f1;
				padding-top: 16upx;
				
				.safety-text {
					font-size: 24upx;
					color: #7f8c8d;
					line-height: 1.4;
				}
			}
		}
	}
</style>

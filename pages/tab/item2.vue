<template>
	<view class="container">
		<view class="content" >
			<block v-for="(item, index) in videos" :key="index">
				<view class="card" @tap="itemCarChange(item)">
					<view class="card-header">
						<view class="card-type">{{getRunningStatusText(item.runningStatus)}}</view>
						<view class="card-user">
							<view class="status-info">
								<text class="speed">{{item.speed || 0}}km/h</text>
								<text class="temperature">{{item.temperature || '--'}}°C</text>
							</view>
						</view>
					</view>
					
					<view class="card-body">
						<text class="card-title">{{item.currentStationName}} → {{item.nextStationName}}</text>
						<text class="card-content">
							到达时间：{{item.arrivalTime || '--'}}
							<br>发车时间：{{item.departureTime || '--'}}
						</text>
						<view class="status-row">
							<text class="status-item">供电：{{getPowerStatusText(item.powerStatus)}}</text>
							<text class="status-item">车门：{{getDoorStatusText(item.doorStatus)}}</text>
							<text class="status-item">维护：{{getMaintenanceStatusText(item.maintenanceStatus)}}</text>
						</view>
						<text class="fault-desc" v-if="item.faultDescription">故障：{{item.faultDescription}}</text>
					</view>
					
					<view class="card-footer">
						<text class="post-time">更新时间：{{item.updateTime || item.createTime || '刚刚'}}</text>
					</view>
				</view>
			</block>
		</view>
		
	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				videos: []
			}
		},
		onShow: function() {
			var that = this;
			uni.request({
				url: uni.getStorageSync("url") + '/emu-dynamic-info/findAll',
				method: "POST",
				header: {
					'content-type': 'application/x-www-form-urlencoded'
				},
				success: function(res) {
					if(res.data.code == 0) {
						that.videos = res.data.data;
						console.log("动车组动态信息: " + JSON.stringify(that.videos));
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
		methods: {
			itemCarChange: function(list) {
				var json = JSON.stringify(list);
				uni.navigateTo({
					url: "../detail/detail?data=" + json
				})
			},
			getRunningStatusText: function(status) {
				const statusMap = {
					0: '待发',
					1: '运行中',
					2: '到站',
					3: '停运'
				};
				return statusMap[status] || '未知';
			},
			getPowerStatusText: function(status) {
				return status === 1 ? '正常' : '断电';
			},
			getDoorStatusText: function(status) {
				return status === 1 ? '开启' : '关闭';
			},
			getMaintenanceStatusText: function(status) {
				return status === 1 ? '需维护' : '正常';
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5;
		height: 100%;
		font-size: 28upx;
		line-height: 1.8;
	}
	
	.container {
		margin-bottom: 10upx;
		padding-bottom: 120upx;
	}
	
	.header {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		z-index: 100;
		background: #ffffff;
		height: 88upx;
		display: flex;
		align-items: center;
		justify-content: center;
		box-shadow: 0 2upx 10upx rgba(0,0,0,0.1);
		
		.title {
			font-size: 32upx;
			font-weight: bold;
			color: #333;
		}
	}
	
	.content {
		padding: 20upx 24upx;
	}
	
	.card {
		background: #ffffff;
		border-radius: 20upx;
		margin-bottom: 24upx;
		padding: 24upx;
		box-shadow: 0 2upx 12upx rgba(0,0,0,0.05);
		
		&:active {
			transform: scale(0.98);
			transition: all 0.2s ease;
		}
		
		.card-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 24upx;
			
			.card-type {
				font-size: 26upx;
				color: #2980b9;
				background: rgba(41,128,185,0.1);
				padding: 6upx 20upx;
				border-radius: 24upx;
				
				&.running {
					color: #27ae60;
					background: rgba(39,174,96,0.1);
				}
				
				&.stopped {
					color: #e74c3c;
					background: rgba(231,76,60,0.1);
				}
			}
			
			.status-info {
				display: flex;
				flex-direction: column;
				align-items: flex-end;
				
				.speed {
					font-size: 32upx;
					font-weight: bold;
					color: #2980b9;
					margin-bottom: 4upx;
				}
				
				.temperature {
					font-size: 24upx;
					color: #e67e22;
				}
			}
		}
		
		.card-body {
			padding: 0 4upx;
			
			.card-title {
				font-size: 34upx;
				font-weight: bold;
				color: #333;
				margin-bottom: 16upx;
				display: block;
				line-height: 1.4;
			}
			
			.card-content {
				font-size: 28upx;
				color: #666;
				line-height: 1.6;
				margin-bottom: 16upx;
			}
			
			.status-row {
				display: flex;
				justify-content: space-between;
				margin-bottom: 12upx;
				
				.status-item {
					font-size: 24upx;
					color: #95a5a6;
					background: #ecf0f1;
					padding: 4upx 12upx;
					border-radius: 12upx;
				}
			}
			
			.fault-desc {
				font-size: 26upx;
				color: #e74c3c;
				background: rgba(231,76,60,0.1);
				padding: 8upx 12upx;
				border-radius: 8upx;
				margin-top: 8upx;
				display: block;
			}
		}
		
		.card-footer {
			margin-top: 24upx;
			padding-top: 24upx;
			border-top: 2upx solid #f5f5f5;
			
			.post-time {
				font-size: 24upx;
				color: #999;
			}
		}
	}
	
	.fab-button {
		position: fixed;
		right: 40upx;
		bottom: 40upx;
		width: 110upx;
		height: 110upx;
		background: linear-gradient(135deg, #3498db, #2980b9);
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		box-shadow: 0 4upx 16upx rgba(41,128,185,0.4);
		transition: all 0.3s ease;
		
		&:active {
			transform: scale(0.92);
		}
		
		.iconfont {
			font-size: 52upx;
			color: #ffffff;
		}
	}
	
	@font-face {
		font-family: "iconfont";
		src: url('data:font/woff2;charset=utf-8;base64,d09GMgABAAAAAAO0AAsAAAAACFwAAANlAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHFQGYACDMgqDaIM0ATYCJAMUCwwABCAFhGcHWBtmBxHVm8PIfhzGMck6c2bkTSjPG/H0/7+1d+59M6o0iaB5JZFMgkQoJCiFhE5IhKY7lc5QyP3/f7Q3rVNqt1YCjYFJZYA7ZEwKKc5S2PmqF5CiVLTy/M9h6TQpML9Vc1lz0BhNwGkOtBfGwYIJ5B3If8PYTVzGfQK9ZpWj9rZ2D0EhYbYF4kxlJVBEEkuNQ2uhq2FtEd+goa16Q70E4Kv7+/gPMVFIaszsuHdSVaHyl+Vnw6L0f2nzKUB/ngX2KjKWgULc1Bov8oS8ZZ5er4vNtgFoNYrE1+Az9rNh0f+fOF0i9Zr/8EiRBNEZmXUwh0bxy5AIvwypwZdN6vBlkQZ8NhTQB9/7NwF6cQ+8JxbFD0Th0YPNm7MiN28u3LZtUWR0dFR0TEwkuLw8bXwXL6ZevZp+82Z6NFjxVwlXbtwAC1+/nn79esb168tev54JLkq/kXHt2qNr1+BaVDiIjo+G2Bs3VHQeNcAhAcSEhBmxe9euWbFbt6YhbCa4fv0qQnR0RDwEo8jVq1nXrq1E2Kzr1zPQeQS6Ll7MuHIl9erVjGvXlqLz0vv3M+7dS8/Pz7x7Nw2dRxQUpF2+vPT+/cUFBWmXLqVHYEKi4+LCYxMTw2Pj4yNiEhMj0Yy4hISo2OTkqNikpEjkNzomJio2Li4iJj4+Mhp5jYuLjIlPjg0Kj0mMDQqLSQgPxGYGhsUmhgcFRyfGhIaGxSaHhwXGJIUHBcfEJ4cGhcUlhQcGRMfHhQQGxMSFBgbGxIcGBUTHxwQHQKUPYGbmFvwPp079b5tr/f/2bZZk7e3s7P/dJjhqK/5fy7zdf7e8CV/9NznV3/TpVHXqv81Tg/6TnKxO1jc1L/8bNgD4v+Td6s7ojcNv5kz4EH+FLo7l/6tZoF/rr0gvyl8oK0Wg/CuZeYeqpWjR7SUbGO8P9Or1UUgv2T+pGzagHJIhaTMJWa0ZZMEuQ6PLKrRqtwe9lqgc7zOEE0VpwKIJAYRBeyHp9x2yQRfIgn0JjVHfoNVgCPS6Cs0T+8yHg6J1kQ3QP1QRjrCyksj4EQ17JxN1x4n4F3LHCzBrqou4xA55wCnmGTayjd2ogOGYgBk4h+MwwDBKYYkpZYVYr6Io7VWEIywX2QDqH6oIR7BmT6L8/hEa9k7GnxAn0l+QO7EGmGkUVeYl7VBb5Kmc57CjPM52QwUwHEwAM8DjcDgYgEHRh1hEKYWcgtJKURR1V1bH36rfB71gNh2ZCAkp0hCtPE7VrQcj6wSnF+dSAgAAAA==') format('woff2');
	}
	
	.iconfont {
		font-family: "iconfont" !important;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}
	
	.icon-eye:before {
		content: "\e78f";
	}
	
	.icon-message:before {
		content: "\e78a";
	}
	
	.icon-add:before {
		content: "\e767";
	}
</style>

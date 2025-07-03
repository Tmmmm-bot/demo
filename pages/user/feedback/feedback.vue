<template>
    <view class="page">
        <view class='feedback-title'>
            <text>意见反馈</text>
            <text class="feedback-quick" @tap="chooseMsg">快速键入</text>
        </view>
        <view class="feedback-body">
            <textarea placeholder="请详细描述你的内容..." v-model="sendDate.content" class="feedback-textare" />
        </view>
      
        <view class='feedback-title'>
            <text>姓名</text>
        </view>
        <view class="feedback-body">
            <input class="feedback-input" v-model="sendDate.name" placeholder="姓名" />
        </view>
        <view class='feedback-title'>
            <text>电话</text>
        </view>
        <view class="feedback-body">
            <input class="feedback-input" v-model="sendDate.contact" placeholder="(选填,方便系统回访 )" />
        </view>
		
       
        <button type="primary" class="feedback-submit" @tap="send">提交</button>
        <view class='feedback-title'>
        </view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
				feeds:[],
                msgContents: ["软件好棒", "非常好"],
                stars: [1, 2, 3, 4, 5],
                imageList: [],
                sendDate: {
                    score: 0,
                    content: "",
					name:"",
                    contact: ""
                }
            }
        },
        onUnload() {
            this.imageList = [];
            this.sendDate = {
                score: 0,
                content: "",
                contact: ""
            }
        },
        onLoad() {
            
        },
        methods: {
            chooseMsg() { //快速输入
                uni.showActionSheet({
                    itemList: this.msgContents,
                    success: (res) => {
                        this.sendDate.content = this.msgContents[res.tapIndex];
                    }
                })
            },
            chooseImg() { //选择图片
                uni.chooseImage({
                    sourceType: ["camera", "album"],
                    sizeType: "compressed",
                    count: 9,
                    success: (res) => {
                        this.imageList = res.tempFilePaths;
                    }
                })
            },
            chooseStar(e) { //点击评星
                this.sendDate.score = e;
            },
            previewImage() { //预览图片
                uni.previewImage({
                    urls: this.imageList
                });
            },
            send() { //发送反馈
				var cont = this.sendDate.content;
				var name = this.sendDate.name;
				var tel =   this.sendDate.contact;
				
				if(cont==""){
					this.showMessage("请请输入描述！")
					return
				}
				
				var that = this;
					uni.request({
								url: uni.getStorageSync("url") +'/feedback/add', //仅为示例，并非真实接口地址。
								method: "POST",
								data:{
									content:cont,
									userName:name,
									userTel:tel
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded'
								  },
								success: function (res) {
										uni.showToast({
											title:"反馈成功"
										})
								},
								fail:function(res){
									var obj = JSON.stringify(res);
									console.log(obj);
								}
				});
                
            }
        }
    }
</script>

<style>
    page {
        background-color: #EFEFF4;
    }

    .input-view {
        font-size: 28upx;
    }
</style>

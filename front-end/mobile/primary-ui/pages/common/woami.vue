<template>
	<view class="content">
		<text class="title">{{ title }}</text>
		<text class="title">{{ message }}</text>
		<u-back-top :scroll-top="scrollTop"></u-back-top>

	</view>
</template>


<script>
	export default {
		data() {
			return {
				scrollTop: 0,
				title: "提交的队列 可以查询当前进度",
				message: "查看历史记录",
				disabled: false,
			};
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},

		onLoad() { },

		methods: {

		},
	};
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: $u-primary;
	}
	.wrap {
		height: 200vh;
	}
	.swiper-item {
		display: block;
		height: 300rpx;
		line-height: 300rpx;
		text-align: center;
	}
</style>
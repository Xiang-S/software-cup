<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
	</view>
	
</template>


<script>
	export default {
		data() {
			return {
				title: '暂时不知道放什么，暂时用作占位符\n 将来可能有其他放面的用处',
			}
		},
		onLoad() {

		},
		methods: {

			changeIndicatorDots(e) {
				this.indicatorDots = !this.indicatorDots
			},
			changeAutoplay(e) {
				this.autoplay = !this.autoplay
			},
			intervalChange(e) {
				this.interval = e.detail.value
			},
			durationChange(e) {
				this.duration = e.detail.value
			}
		}
	}
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
		color: #8f8f94;
	}

	.swiper-item {
		display: block;
		height: 300rpx;
		line-height: 300rpx;
		text-align: center;
	}
</style>
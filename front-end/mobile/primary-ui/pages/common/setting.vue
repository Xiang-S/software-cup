<template>
  <view>
    <u-collapse
    @change="change"
    @close="close"
    @open="open"
  >
    <u-collapse-item
      title="登录设置"
      name="login info"
    >
      <text class="u-collapse-content">将来用于处理用户登录相关接口，包括个人信息</text>
    </u-collapse-item>
    
    <u-collapse-item
      title="权限设置"
      name="permission settings"
    >
      <text class="u-collapse-content">包含Android操作系统相关的权限设置</text>
    </u-collapse-item>

    <u-collapse-item
      title="服务器设置"
      name="server"
    >
      <text class="u-collapse-content">包括杂七杂八的调试功能</text>
    </u-collapse-item>

    <u-collapse-item
    title="关于"
    name="other"
  >
    <text class="u-collapse-content">包含软件许可和一些乱七八糟的东西</text>
  </u-collapse-item>

  </u-collapse>

  
  <view class="content">
		
		<view class="text">
				<view style="padding: 20px;">
					<u-button type="primary" text="退出登录"></u-button>
					<u-button type="primary" :disabled="disabled" text="注销账户"></u-button>
				</view>
		</view>
	</view>
  </view>
</template>

<script>
	export default {
    data() {
      return {
        disabled: true
      }
    },
		methods: {
            open(e) {
              // console.log('open', e)
            },
            close(e) {
              // console.log('close', e)
            },
            change(e) {
              // console.log('change', e)
            },
            handleClick() {
      // 点击按钮后执行的操作
    }
        }
	}
</script>
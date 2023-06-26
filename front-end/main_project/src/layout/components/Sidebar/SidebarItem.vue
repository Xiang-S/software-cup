<template>
  <div>
    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <div v-if="item.path[0] === '/'">
          <el-menu-item :index="item.path">
            <item :icon="item.icon" :title="item.name"/>
          </el-menu-item>
        </div>
        <div v-else>
          <a :href="item.path" target="_blank">
            <el-menu-item index="#">
              <item :icon="item.icon" :title="item.name"/>
            </el-menu-item>
          </a>
        </div>
      </div>
      <!-- <app-link :to="resolvePath(onlyOneChild.path)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)" :class="{'submenu-title-noDropdown':!isNest}">
          <item :icon="onlyOneChild.icon" :title="onlyOneChild.title" />
      </el-menu-item>
      </app-link> -->
      <div v-else>
        <el-submenu ref="subMenu" :index="item.id + ''" popper-append-to-body>
          <template slot="title">
            <item :icon="item.icon" :title="item.name"/>
          </template>
          <!-- <sidebar-item
            v-for="child in item.children"
            :key="child.id"
            :is-nest="true"
            :item="child"
            :base-path="resolvePath(child.path)"
            class="nest-menu"
          /> -->
          <div v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path">
              <item :icon="subItem.icon" :title="subItem.name"/>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
  </div>
</template>

<script>
import path from 'path'
import { isExternal } from '@/utils/validate'
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

export default {
  name: 'SidebarItem',
  components: { Item },
  mixins: [FixiOSBug],
  props: {
    // route object
    isNest: {
      type: Boolean,
      default: false
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    // To fix https://github.com/PanJiaChen/vue-admin-template/issues/237
    // TODO: refactor with render function
    return {
      menus: localStorage.getItem('menus')
        ? JSON.parse(localStorage.getItem('menus'))
        : [],
      opens: localStorage.getItem('menus')
        ? JSON.parse(localStorage.getItem('menus')).map((v) => v.id + '')
        : []
    }
  },
  methods: {
    hasOneShowingChild(children = [], parent) {
      const showingChildren = children.filter((item) => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })

      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }

      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ...parent, path: '', noShowingChildren: true }
        return true
      }

      return false
    },
    resolvePath(routePath) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>

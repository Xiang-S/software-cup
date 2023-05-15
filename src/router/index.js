import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes picture mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '大数据管理', icon: 'el-icon-s-data' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    redirect: '/user/table',
    name: 'user',
    meta: { title: '用户管理', icon: 'el-icon-user-solid' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/user/table/index'),
        meta: { title: '用户信息管理', icon: 'table' }
      },
      {
        path: 'authority',
        name: 'authority',
        component: () => import('@/views/user/authority/index'),
        meta: { title: '用户权限管理', icon: 'tree' }
      }
    ]
  },
  {
    path: '/picture',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Picture',
        component: () => import('@/views/picture/index'),
        meta: {
          title: '图片管理',
          icon: 'el-icon-picture'
        },
      }
    ]
  },
  {
    path: '/repository',
    component: Layout,
    redirect: '/repository/disease-prevention',
    name: 'Repository',
    meta: { title: '知识库', icon: 'el-icon-info' },
    children: [
      {
        path: 'disease-prevention',
        component: () => import('@/views/repository/disease-prevention/index.vue'),
        meta: { title: '植物病害防害知识库', icon: 'el-icon-s-management' }
      },
      {
        path: 'agricultural-knowledge',
        component: () => import('@/views/repository/agricultural-knowledge/index.vue'),
        meta: { title: '农业知识技术库', icon: 'el-icon-s-management' }
      }
    ]
  },
  {
    path: '/publish',
    component: Layout,
    redirect:'/publish',
    children: [
      {
        path: 'index',
        component: () => import('@/views/publish/index.vue'),
        meta: { title: '助农信息发布', icon: 'el-icon-s-promotion' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

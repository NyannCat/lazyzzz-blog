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
 *                                it will becomes nested mode, otherwise not show the root menu
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
    path: '/install',
    component: () => import('@/views/install/index'),
    hidden: true
  },

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
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/article',
    component: Layout,
    name: 'article',
    redirect: '/article/table',
    meta: { title: '文章管理', icon: 'el-icon-reading'},
    children: [
      {
        path: 'table',
        name: 'ArticleTable',
        component : () => import('@/views/table/article/index'),
        meta: { title: '所有文章', icon: 'el-icon-s-order'}
      },
      {
        path: 'editor',
        name: 'Editor',
        component : () => import('@/views/editor/index'),
        meta: { title: '撰写文章', icon: 'el-icon-edit'}
      }
    ]
  },

  {
    path: '/category',
    component: Layout,
    name: 'category',
    redirect: '/category/table',
    children: [
      {
        path: 'table',
        name: 'CategoryTable',
        component : () => import('@/views/table/category/index'),
        meta: { title: '分类管理', icon: 'el-icon-collection'}
      }
    ]
  },

  {
    path: '/comment',
    component: Layout,
    name: 'comment',
    redirect: '/comment/table',
    children: [
      {
        path: 'table',
        name: 'CommentTable',
        component : () => import('@/views/table/comment/index'),
        meta: { title: '评论管理', icon: 'el-icon-s-order'}
      }
    ]
  },

  {
    path: '/attachment',
    component: Layout,
    children : [
      {
        path: 'index',
        name: 'attachment',
        component: () => import('@/views/attachment/index'),
        meta: { title: '附件管理', icon: 'el-icon-picture-outline'},
      }
    ]
  },

  {
    path: '/setting',
    component: Layout,
    children: [
      {
        path: 'user',
        name: 'UserConfig',
        component : () => import('@/views/user/index'),
        meta: { title: '用户信息', icon: 'el-icon-user'}
      },
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

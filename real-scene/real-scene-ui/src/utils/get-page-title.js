import defaultSettings from '@/settings'

const title = defaultSettings.title || '三维实景地图应用平台管理系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}

const getters = {
  sidebar: state => state.app.sidebar,
  permissions: state => state.user.permissions,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  username: state => state.user.username,
  account: state => state.user.account,
  userid: state => state.user.userid
}
export default getters

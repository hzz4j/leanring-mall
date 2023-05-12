/**
 * 商城Vuex-mutations
 */
export default {
  saveToken(state,token){
    state.token=token;
  },
  saveUserName(state, username) {
    state.username = username;
  },
  saveCartCount(state, count) {
    state.cartCount = count;
  },
  saveMemberId(state,memberId){
    state.memberId=memberId;
  },
  saveNickName(state,nickName){
    state.nickName=nickName;
  }
}
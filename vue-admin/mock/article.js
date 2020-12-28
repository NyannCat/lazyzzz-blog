const Mock = require('mockjs')

const data = Mock.mock({
  'records|20': [{
    id: '@id',
    title: '@csentence(5,10)',
    views: '@integer(10,100)',
    likes: '@integer(10, 100)',
    shares: '@integer(10,100)',
    comments: '@integer(10,100)',
    type: '@integer(0,1)',
    createTime: '@datetime',
    updateTime: '@datetime',
    categoryName: '@cword(3)'
  }]
})

module.exports = [
  {
    url: '/article/all',
    type: 'get',
    response: config => {
      const records = data.records
      return {
        code: 200,
        msg: '',
        data: {
          records: records,
          total: records.length,
          size: 10
        }
      }
    }
  }
]

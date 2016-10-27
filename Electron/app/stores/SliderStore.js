import BaseStore from "../services/BaseStore"

function SliderStore() {
    const service = BaseStore({
      currentPage: 1,
      maxPage: 4
    });

    service.nextSlide = function () {
      if(this.data.currentPage < this.data.maxPage){
        this.data = {
          currentPage: this.data.currentPage + 1
        }
      }

    };

    service.prevSlide = function () {
      if(this.data.currentPage > 1){
        this.data = {
          currentPage: this.data.currentPage - 1
        }
      }
    };

    return service;
}

module.exports = SliderStore();

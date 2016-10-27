import BaseStore from "../services/BaseStore"

function SliderStore() {
    const service = BaseStore({
      currentPage: 0
    });

    service.nextSlide = function () {
        this.data = {
          currentPage: this.data.currentPage + 1
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

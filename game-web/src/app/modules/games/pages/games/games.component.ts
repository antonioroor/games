import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { take } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Game } from '../../../../core/interfaces/game.interface';
import { GameModalComponent } from '../../components/game-modal/game-modal.component';
import { GameService } from '../../../../core/services/game/game.service';
import { TranslateConfigModule } from '../../../../shared/modules/translate-config/translate-config.module';

@Component({
  selector: 'app-games',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    NgbPaginationModule,
    TranslateModule,
    TranslateConfigModule,
    GameModalComponent
  ],
  providers: [],
  templateUrl: './games.component.html'
})
export class GamesComponent implements OnInit {

  protected games: Game[];
  protected gamesToShow: Game[];
  protected page: number;
  protected pageSize: number;
  protected showModal: boolean;
  protected idGame: number;

  constructor(
    protected translate: TranslateService,
    protected gameService: GameService,
    protected toast: ToastrService
  ) {
    this.translate.setDefaultLang('es');
    this.showModal = false;
    this.games = [];
    this.gamesToShow = [];
    this.page = 1;
    this.pageSize = 5;
    this.idGame = 0;
  }

  ngOnInit(): void {
    this.findAllGames();
  }

  /**
   * Obtiene la lista de juegos
   */
  public findAllGames(): void {
    this.gameService.findAllGames().pipe(take(1)).subscribe({
        next: (res) => {
          if (res) {
            this.games = res;
            this.gamesToShow = res;
          } else {
            this.translate.get('error').subscribe((msg: string) => {
              this.toast.error(msg);
            });
          }
        },
        error: () => {
          this.translate.get('error').subscribe((msg: string) => {
            this.toast.error(msg);
          });
        }
      });
  }

  /**
   * Settea el id del registro a eliminar
   */
  protected setIdGameToDelete(idGame: any) {
    this.idGame = idGame;
  }

  /**
   * Recarga la tabla tras la eliminación de un registro
   */
  protected reloadTable(event: any) {
    if (event === 'gameDeleted') {
      this.translate.get('deleteOk').subscribe((msg: string) => {
        this.toast.success(msg);
      });
      this.findAllGames();
    }
  }

}

